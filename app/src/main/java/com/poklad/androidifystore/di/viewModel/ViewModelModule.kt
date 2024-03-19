package com.poklad.androidifystore.di.viewModel

import androidx.lifecycle.ViewModel
import com.poklad.androidifystore.di.annotations.ViewModelKey
import com.poklad.androidifystore.presentation.ui.screens.all_products.AllProductsViewModel
import com.poklad.androidifystore.presentation.ui.screens.auth.forgot_password.ResetPasswordViewModel
import com.poklad.androidifystore.presentation.ui.screens.auth.logIn.LoginViewModel
import com.poklad.androidifystore.presentation.ui.screens.auth.signup.SignUpViewModel
import com.poklad.androidifystore.presentation.ui.screens.categories.CategoriesViewModel
import com.poklad.androidifystore.presentation.ui.screens.home.HomeViewModel
import com.poklad.androidifystore.presentation.ui.screens.product_details.ProductDetailsViewModel
import com.poklad.androidifystore.presentation.ui.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllProductsViewModel::class)
    abstract fun bindYourViewModel(yourViewModel: AllProductsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailsViewModel::class)
    abstract fun bindProductDetailsViewModel(productDetailsViewModel: ProductDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun bindResetPasswordViewModel(resetPasswordViewModel: ResetPasswordViewModel): ViewModel
}

