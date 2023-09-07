package com.poklad.androidifystore.presentation.ui.screens.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.poklad.androidifystore.R
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentHomeBinding
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.toast
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.mapper.ProductItemToProductItemUi
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import com.poklad.androidifystore.presentation.ui.screens.product_details.ProductDetailsFragment
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private val womenAdapter: WomenListAdapter by lazy {
        WomenListAdapter()
    }

    private val menAdapter: MenListAdapter by lazy {
        MenListAdapter()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@HomeFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpImageSlider()
        navigateToCategories()
        initRecyclerMen()
        initRecyclerWomen()
        setUPLists()
        openMensProductList()
        openWomensProductList()
    }

    //todo BUG:When I go to the CATEGORY screen, I can't get back to the HOME screen
    private fun navigateToCategories() {
        binding.buttonToCategories.setOnClickListener {
            navigateToFragment(R.id.action_fragmentHome_to_categoriesFragment, null)
        }
    }

    private fun openMensProductList() {
        binding.textViewMens.setOnClickListener {
            requireContext().toast("Open Men list")
        }
    }

    private fun openWomensProductList() {
        binding.textViewWomens.setOnClickListener {
            requireContext().toast("Open Women list")
        }
    }

    /*    private fun setUp() {
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.menClothesList.collect { resource ->
                        when (resource) {
                            is Resource.Loading -> {
                                binding.apply {
                                    recyclerViewWomenClothes.invisible()
                                    progressBarMenList.visible()
                                }
                            }

                            is Resource.Success -> {
                                binding.apply {
                                    progressBarMenList.invisible()
                                    renderMenList (resource.data)
                                    recyclerViewWomenClothes.invisible()
                                }
                            }

                            is Resource.Error -> {
                                // Показать ошибку с помощью Snackbar или Toast
                                Snackbar.make(
                                    binding.root,
                                    resource.throwable?.localizedMessage ?: "Unknown error",
                                    Snackbar.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                }
            }
        }*/
    private fun setUPLists() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingFlow.collect { showLoader ->
                    if (showLoader) {
                        binding.apply {
                            //                            progressBarMenList.visible()
                            //                            progressBarMenList.visible()
                            recyclerViewMenClothes.invisible()
                            recyclerViewWomenClothes.invisible()
                        }
                    } else {
                        binding.apply {
                            //                            progressBarMenList.invisible()
                            //                            progressBarWomenList.invisible()
                            recyclerViewMenClothes.visible()
                            recyclerViewWomenClothes.visible()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorWomanList.collect { message ->
                //                log(message.toString())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.womenClothesList.collect {
                    renderWomenList(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.menClothesList.collect {
                    if (it.isEmpty()) {
                        //hide this section
                    }
                    binding.apply {
                        renderMenList(it)
                    }
                }
            }
        }
    }

    private fun initRecyclerWomen() {
        setUpRecyclerView(
            womenAdapter,
            binding.recyclerViewWomenClothes,
            LinearLayoutManager.HORIZONTAL
        ) { productItem ->
            val product = ProductItemToProductItemUi().map(productItem)
            navigateToFragment(
                R.id.action_fragmentHome_to_productDetailsFragment,
                bundleOf(ProductDetailsFragment.ARG_PRODUCT to product)
            )
        }
    }

    private fun initRecyclerMen() {
        setUpRecyclerView(
            menAdapter,
            binding.recyclerViewMenClothes,
            LinearLayoutManager.HORIZONTAL
        ) { productItem ->
            val product = ProductItemToProductItemUi().map(productItem)
            navigateToFragment(
                R.id.action_fragmentHome_to_productDetailsFragment,
                bundleOf(ProductDetailsFragment.ARG_PRODUCT to product)
            )
        }
    }

    private fun renderWomenList(productsList: List<ProductItem>) {
        womenAdapter.list = productsList
    }

    private fun renderMenList(productsList: List<ProductItem>) {
        menAdapter.list = productsList
    }

    private fun setUpImageSlider() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageList.collect { imageList ->
                    val sliderAdapter = SliderAdapter(imageList)
                    binding.imageSlider.apply {
                        setSliderAdapter(sliderAdapter)
                        isAutoCycle = true
                        setIndicatorAnimation(IndicatorAnimationType.THIN_WORM)
                        setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                        autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_RIGHT
                        startAutoCycle()
                    }
                }
            }
        }
    }
}