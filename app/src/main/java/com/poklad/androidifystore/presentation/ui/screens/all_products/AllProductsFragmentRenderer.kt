///*
//package com.poklad.androidifystore.presentation.ui.screens.all_products
//todo thinking how to transfer the data
//class AllProductsFragmentRenderer @Inject constructor() {
//    fun render(
//        binding: FragmentAllProductsBinding,
//        resource: Resource<List<ProductItem>>
//    ) {
//        when (resource) {
//            is Resource.Success -> {
//                binding.apply {
//                    progressBarAllProducts.invisible()
//                    renderList(resource.data)
//                    recycleViewProductList.visible()
//                }
//            }
//
//            is Resource.Loading -> {
//                binding.apply {
//                    progressBarAllProducts.visible()
//                    recycleViewProductList.invisible()
//                }
//            }
//
//            is Resource.Error -> {
//                binding.progressBarAllProducts.visible()
//                Toast.makeText(
//                    binding.root.context,
//                    resource.throwable.toString(),
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//        }
//    }
//
//    private fun renderList(data: List<ProductItem>) {
//
//    }
//}*/
