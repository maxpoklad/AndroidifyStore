package com.poklad.androidifystore.presentation.ui.screens.product_details

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.poklad.androidifystore.R
import com.poklad.androidifystore.StoreApp
import com.poklad.androidifystore.databinding.FragmentProductDetailsBinding
import com.poklad.androidifystore.databinding.TestBinding
import com.poklad.androidifystore.domain.model.ProductItem
import com.poklad.androidifystore.extensions.invisible
import com.poklad.androidifystore.extensions.visible
import com.poklad.androidifystore.presentation.mapper.ProductItemToProductItemUi
import com.poklad.androidifystore.presentation.model.ProductItemUi
import com.poklad.androidifystore.presentation.ui.base.BaseFragment
import com.poklad.androidifystore.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding, BaseViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    override val viewModel: ProductDetailsViewModel by viewModels {
        viewModelFactory
    }

    private val adapter: SpecificCategoryListAdapter by lazy {
        SpecificCategoryListAdapter()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentProductDetailsBinding {
        return FragmentProductDetailsBinding.inflate(inflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        StoreApp.daggerComponent.inject(this@ProductDetailsFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * todo I know this is deprecated, but it is test. I will use safeArgs
         */
        val product =
            requireArguments().getParcelable<ProductItemUi>(ARG_PRODUCT)
        binding.apply {
            titleTextView.text = product?.title
            priceTextView.text = "$${product?.price}"
            descriptionTextView.text = product?.description
            Glide.with(this@ProductDetailsFragment)
                .load(product?.image)
                .into(productImageView)

            val ratingText = "4.5"
            val reviewsText = " (245 reviews)"
            val spannable = SpannableStringBuilder(ratingText + reviewsText)
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                ratingText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                StyleSpan(Typeface.NORMAL),
                ratingText.length,
                spannable.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            ratingTextView.text = spannable
        }
        initRecyclerView()
        product?.id?.let { productId ->
            viewModel.fetchData(
                category = product.category,
                productId = productId
            )
        }
        setUpObserver()
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingFlow.collect { showLoader ->
                    if (showLoader) {
                        binding.apply {
                            progressbarRecyclerView.visible()
                            horizontalRecyclerView.invisible()
                        }
                    } else {
                        binding.apply {
                            progressbarRecyclerView.invisible()
                            horizontalRecyclerView.visible()
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productList.collect { productsList ->
                renderList(productsList)
            }
        }
    }


    private fun initRecyclerView() {
        setUpRecyclerView(
            adapter,
            binding.horizontalRecyclerView,
            LinearLayoutManager.HORIZONTAL
        ) { productItem ->
            val product = ProductItemToProductItemUi().map(productItem)
            navigateToFragment(
                R.id.action_productDetailsFragment_self,
                bundleOf(ARG_PRODUCT to product)
            )
        }
    }

    private fun renderList(productsList: List<ProductItem>) {
        adapter.list = productsList
    }

    companion object {
        const val ARG_PRODUCT = "product"
    }
}