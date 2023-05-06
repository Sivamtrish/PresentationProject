package com.example.presentationproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExpandableCardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_card_view);

    }
}
//
//        DispatchingAndroidInjector<Object> buaInjector;
//        private AccountListView fundAccountListView;
//        private CurrencyEditText currencyEditText;
//
//        private String selectedCompanyItem, selectedProductTypeItem;
//        private TextView buaCustomerName, accountNumberBankName;
//        private LinearLayout account_number_name_ll, ll_total_amount, customer_name_ll;
//        private EditText customerIdEt, companyAccountNumberEt, descriptionEt;
//        private AccountResponse senderAccount;
//        //This adds the 0 digit to the right
//        private BigDecimal amount = new BigDecimal(0);
//
//        //These paases the authentication type stored in appConstants into auth type
//        private String authType = AppConstants.AUTHENTICATION_TYPE_PIN;
//        //This enteredPin variable holds the value of the pin entered by the user.
//        private String enteredPin, secondFactorValue;
//        private BuaProductType pdtTypeModel;
//
//        //This checks if the user has exceeded his limit
//        private Boolean hasExceededLimit;
//        private BuaCompany buaCompany;
//        private boolean isOpenSettings;
//
//
//        @Override
//        public int getBindingVariable() {
//            return BR.viewModel;
//        }
//
//        @Override
//        public int getLayoutId() {
//            return R.layout.activity_bua;
//        }
//
//        @Override
//        public BuaActivityViewModel getViewModel() {
//            return buaActivityViewModel;
//        }
//
//
//        @Override
//        protected int getToolbarTitle() {
//            return R.string.action_back;
//        }
//
//        public static void start(Context context) {
//            Intent starter = new Intent(context, BuaActivity.class);
//            context.startActivity(starter);
//        }
//
//        @Override
//        protected void initComponents() {
//            fundAccountListView = getViewDataBinding().fundingAcct;
//            customerIdEt = getViewDataBinding().etCustomerId;
//            descriptionEt = getViewDataBinding().etDescription;
//            currencyEditText = getViewDataBinding().amountLayout.etAmount;
//            buaCustomerName = getViewDataBinding().customerName;
//            account_number_name_ll = getViewDataBinding().accountNumberNameLl;
//            accountNumberBankName = getViewDataBinding().accountNumberBankName;
//            ll_total_amount = getViewDataBinding().llTotalAmount;
//            customer_name_ll = getViewDataBinding().customerNameLl;
//
//
//            /**The nex thing is to get the feature scheme code**/
//
//            getViewModel().getFeatureSchemeCodes();
//            subscribeToLiveData();
//            buildAccounts();
//            initListeners();
//        }
//
//        private void subscribeToLiveData() {
//            buaActivityViewModel.getFeatureSchemeCodesLiveData().observe(this, this::processFeatureSchemeCodesLiveData);
//            buaActivityViewModel.getAccountsLiveData().observe(this, this::processCustomerAccounts);
//            buaActivityViewModel.getBuaCompanyLiveData().observe(this, this::processGetBuaCompaniesList);
//            buaActivityViewModel.getBuaProductsLiveData().observe(this, this::processBuaProducts);
//            buaActivityViewModel.getCustomerOrderIdLiveData().observe(this, this::processBuaCustomerOrderId);
//            buaActivityViewModel.getBuaPaymentLiveData().observe(this, this::processBuaPaymentResponse);
//            buaActivityViewModel.get2FaLiveData().observe(this, this::process2FaLimitResponse);
//            buaActivityViewModel.getGetOtpLiveData().observe(this, this::processOtpResponse);
//        }
//
//        /**
//         *  makes the payment for bua
//         */
//        private void makeBuaPaymentRequest() {
//            buaActivityViewModel.executeBuaPayment(
//                    secondFactorValue,
//                    currencyEditText.getRawValue(),
//                    senderAccount.getAccountNumber(),
//                    enteredPin,
//                    buaCompany.getId(),
//                    buaCompany.getName(),
//                    getViewDataBinding().etCustomerId.getText().toString().trim(),
//                    AppConstants.APP_CLIENT_ID,
//                    pdtTypeModel.getId(),
//                    authType,
//                    getViewDataBinding().etDescription.getText().toString(),
//                    Tools.getDeviceId(this)
//            );
//        }
//
//
//        //get the feature scheme code to know if User's account is blacklisted. there are different scheme codes for different events.
//        //This shows the account
//        private void processFeatureSchemeCodesLiveData(FeatureSchemeCode featureSchemeCode) {
//            if (featureSchemeCode != null) {
//                fundAccountListView.initAdapter(featureSchemeCode.getTransfer());
//                getViewModel().getAccounts();
//            }
//        }
//
//        //Here we get the account with name, accountNumber, accountType, Balance and currency code
//        private void buildAccounts() {
//            fundAccountListView.addOnAccountSelectedListener(accountResponse -> {
//                senderAccount = (AccountResponse) accountResponse;
//                ViewAnimation.fadeIn(getViewDataBinding().btnMakePayment);
//                enableDisableBtn();
//            });
//        }
//
//        private void processCustomerAccounts(Resource<List<AccountResponse>> listResource) {
//            switch (listResource.status) {
//                case ERROR:
//                    break;
//                case LOADING:
//                    break;
//                case SUCCESS:
//                    List<AccountResponse> accountResponses = new ArrayList<>();
//                    if (listResource.data != null) {
//                        //handles red padlock on use-case
//                        for (AccountResponse acct : listResource.data) {
//                            acct.setIsPadlockNaira(!acct.getCurrencyCode().equals("NGN"));
//                            accountResponses.add(acct);
//                        }
//                    }
//                    fundAccountListView.addAccounts(accountResponses);
//                    //now fire get the companies
//                    buaActivityViewModel.getBuaCompanies();
//                    break;
//            }
//        }
//
//        private void processGetBuaCompaniesList(Resource<BuaCompanyResponse> listResource) {
//            switch (listResource.status) {
//                case ERROR:
//                    hideLoading();
//                    Tools.openSuccessErrorDialog(this, listResource.message, "Bua Companies", false);
//                    break;
//                case LOADING:
//                    showLoading(R.string.mssg_please_wait, R.string.fetching_company_names);
//                    break;
//                case SUCCESS:
//                    hideLoading();
//                    ArrayAdapter<BuaCompany> tenorAdapter = null;
//
//                    if (listResource.data != null) {
//                        List<BuaCompany> buaCompanyList = new ArrayList<>();
//
//                        BuaCompany buaCompany = new BuaCompany();
//                        buaCompany.setName(getString(R.string.select_company));
//                        buaCompanyList.add(buaCompany);
//
//                        for (int i = 0; i < listResource.data.getBuaCompanyList().size(); i++) {
//                            buaCompanyList.add(listResource.data.getBuaCompanyList().get(i));
//                        }
//
//                        tenorAdapter = new ArrayAdapter<>(this,
//                                android.R.layout.simple_spinner_dropdown_item, buaCompanyList);
//                    }
//                    getViewDataBinding().spCompany.setAdapter(tenorAdapter);
//                    getViewDataBinding().spCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            selectedCompanyItem = parent.getSelectedItem().toString();
////                        Log.d("TAG", "onItemSelected: " + parent.getSelectedItem().toString());
//
//                            if (selectedCompanyItem != null && !selectedCompanyItem.equalsIgnoreCase(getString(R.string.select_company))) {
//                                //try and identify which full object was clicked
//                                buaCompany = (BuaCompany) parent.getItemAtPosition(position);
//                                hideLoading();
//                                account_number_name_ll.setVisibility(View.GONE);
//                                //fire the next event
//                                buaActivityViewModel.getBuaProductTypes(buaCompany.getId());
//
//                            }
//                            enableDisableBtn();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                    break;
//            }
//        }
//
//        public void showReceiverAccountDetails(BuaProductType productType) {
//            if (accountNumberBankName != null) {
//                accountNumberBankName.setText(String.format("%s | %s", productType.getAccountName(), productType.getAccountNumber()));
//            }
//        }
//
//        private void processBuaProducts(Resource<BuaProductTypeResponse> listResource) {
//            switch (listResource.status) {
//                case ERROR:
//                    hideLoading();
//                    Tools.openSuccessErrorDialog(this, listResource.message, "Bua Products", false);
//
//                    break;
//                case LOADING:
//                    showLoading(R.string.mssg_please_wait, R.string.fetching_bua_products);
//                    break;
//                case SUCCESS:
//                    hideLoading();
//                    ArrayAdapter<BuaProductType> tenorAdapter = null;
//                    List<BuaProductType> buaProductTypeList = new ArrayList<>();
//
//                    BuaProductType buaProductType = new BuaProductType();
//                    buaProductType.setType(getString(R.string.select_product));
//                    buaProductTypeList.add(buaProductType);
//
//                    if (listResource.data != null) {
//                        for (int i = 0; i < listResource.data.getBuaProductTypes().size(); i++) {
//                            buaProductTypeList.add(listResource.data.getBuaProductTypes().get(i));
//                        }
//
//                        tenorAdapter = new ArrayAdapter<>(this,
//                                android.R.layout.simple_spinner_dropdown_item, buaProductTypeList);
//                    }
//                    getViewDataBinding().spProductType.setAdapter(tenorAdapter);
//                    getViewDataBinding().spProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            selectedProductTypeItem = parent.getSelectedItem().toString();
////                        Log.d("TAG", "onItemSelected: " + parent.getSelectedItem().toString());
//
//                            pdtTypeModel = (BuaProductType) parent.getItemAtPosition(position);
//                            if (pdtTypeModel.getAccountName() != null) {
//                                account_number_name_ll.setVisibility(View.VISIBLE);
//                                showReceiverAccountDetails(pdtTypeModel);
//                            }
////                        Log.d("TAG", "onItemSelected: " + pdtTypeModel.getAccountName() + " " + pdtTypeModel.getAccountNumber());
//                            enableDisableBtn();
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//                        }
//                    });
//                    break;
//            }
//        }
//
//        private void processBuaCustomerOrderId(Resource<BuaCustomerOrderResponseModel> buaResponseModelResource) {
//            switch (buaResponseModelResource.status) {
//                case ERROR:
//                    hideLoading();
//                    Tools.openSuccessErrorDialog(this, buaResponseModelResource.message, "Bua Companies", false);
//                    break;
//                case LOADING:
//                    showLoading(R.string.mssg_please_wait, R.string.verifying_bua_customer_Id);
//                    break;
//                case SUCCESS:
//                    hideLoading();
//                    if (buaResponseModelResource.data.getName() != null) {
//                        customer_name_ll.setVisibility(View.VISIBLE);
//                        showReceiverDetails(buaResponseModelResource.data);
//                    }
//
//            }
//        }
//
//        public void showReceiverDetails(BuaCustomerOrderResponseModel data) {
//            if (buaCustomerName != null) {
//                buaCustomerName.setText(data.getName());
//            }
//        }
//
//        private void processBuaPaymentResponse(Resource<BuaPaymentResponse> resource) {
//            switch (resource.status) {
//                case LOADING:
//                    showLoading(R.string.mssg_making_payment, R.string.please_wait_dots);
//                    break;
//                case SUCCESS:
//                    hideLoading();
//
//                    if (resource.data != null && resource.data.getReceipt() != null) {
//                        EventBus.getDefault().postSticky(new MessageOnDashboardEvent("Your payment is successful. You will receive an SMS notification shortly. ", "Transfer Successful", resource.data.getReceipt()));
//                        DashboardActivity.start(this);
//                    }
//                    break;
//                case ERROR:
//                    hideLoading();
//                    Tools.openSuccessErrorDialog(this, resource.message, "Error", false);
//
//                    break;
//            }
//
//        }
//
//        /**
//         * this handles all click events et al
//         */
//        private void initListeners() {
//           3
//
//            currencyEditText.addTextChangedListener(new AfterTextChangeWatcher() {
//                @Override
//                public void afterTextChanged(String text) {
//                    if ((currencyEditText != null) && (text.length() > 0)) {
//
//                        amount = new BigDecimal(currencyEditText.getRawValue());
//                        getViewDataBinding().tvTotalAmount.setText(Tools.formatToCommaNaira(BuaActivity.this, amount.divide(new BigDecimal(100))));
//
//                        enableDisableBtn();
//                    }
//                }
//            });
//
//            getViewDataBinding().btnMakePayment.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    //This checks if whatever the User typed is less than the accountBalance
//                    if (currencyEditText.getRawValue() / 100 <= senderAccount.getAccountBalance().getAvailableBalance()) {
//                        //now do 2 factor before proceeding with either pin or fingerprint
//                        buaActivityViewModel.do2FALimitCheckForDialogDisplay(currencyEditText.getRawValue(), false);
//                    } else {
//                        Tools.openSuccessErrorDialog(BuaActivity.this,
//                                "This request cannot be completed due to insufficient funds, please fund your account to proceed",
//                                "Insufficient funds", false);
//                    }
//
//                }
//            });
//
//        }
//
//        private void processOtpResponse(Resource<GetOtpResponse> otpResponse) {
//            switch (otpResponse.status) {
//                case LOADING:
//                    showLoading(R.string.mssg_sending_otp, R.string.please_wait_dots);
//                    break;
//                case SUCCESS:
//                    openEnterOtpTokenDialog(false);
//                    hideLoading();
//                    break;
//                case ERROR:
//                    Tools.openSuccessErrorDialog(this, otpResponse.message, "Otp Validation Error",
//                            false);
//                    hideLoading();
//                    break;
//            }
//        }
//
//        private void enableDisableBtn() {
//            getViewDataBinding().btnMakePayment.setEnabled(currencyEditText.getConvertedValue() >= 1
//                    && fundAccountListView.getAccountResponse() != null
//                    && checkIsEditTextNull(getViewDataBinding().etCustomerId
//            ) && !android.text.TextUtils.isEmpty(selectedCompanyItem)
//                    && !selectedCompanyItem.equalsIgnoreCase("Select company"));
//        }
//
//        //First check
//        private void process2FaLimitResponse(Boolean hasExceededLimit) {
//            this.hasExceededLimit = hasExceededLimit;
//            //if the user has exceeded the limit, the boolean will hold true or false
//            Tools.openVerifyUserWithPinDialog(null, this, "Enter your pin to proceed");
//        }
//
//        @Override
//        public void onNotificationActionButtonClicked() {
//            if (isOpenSettings) {
//                BiometricTransactionSettingActivity.start(this);
//            }
//        }
//
//        @Override
//        public AndroidInjector<Object> androidInjector() {
//            return buaInjector;
//        }
//
//        //pin value is retrieved here and if User has exceeded limit the dialog with three options displays
//        @Override
//        public void onVerificationButtonClicked(String fragTag, String pinEntered) {
//            //extract the pin entered
//            this.enteredPin = pinEntered;
//            String verificationMessage = getString(R.string.make_payment_in_bill_payment,
//                    Tools.formatToCommaNaira(this, amount.divide(new BigDecimal(100))),
//                    senderAccount.getAccountNumber(),
//                    senderAccount.getAccountName(), "BUA " + selectedProductTypeItem);
//
//            if (hasExceededLimit) {
//                //show 2fa dialog here
//                Tools.openExceedTransactionBottomDialogWithFingerprint("bua_2FA_3options", this,
//                        verificationMessage, null, null, getViewModel().isBusiness());
//            } else {
//                makeBuaPaymentRequest();
//            }
//        }
//
//        @Override
//        public void onCancelListener(boolean isCanceled) {
//
//        }
//
//        @Override
//        public void onclickCard() {
//
//        }
//
//        @Override
//        public void onClickOtp() {
//            getViewModel().getOtp(senderAccount.getAccountNumber(), AppConstants.VALIDATE_FOR_TRANSACTION);
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.M)
//        private void showFingerPrintDialog() {
//            FingerPrintPasswordUtil fingerPrintPasswordUtil = new FingerPrintPasswordUtil();
//            fingerPrintPasswordUtil.setContext(this);
//            SystemFingerPrintDialog systemFingerPrintDialog = Tools.getSystemFingerPrintDialog(this, this,
//                    SystemFingerPrintDialog.FingerprintRequest.TRANSACTION_WITH_FINGERPRINT_REQUEST);
//
//            if (fingerPrintPasswordUtil.isPersonalKeyCreated() || fingerPrintPasswordUtil.isBusinessKeyCreated()) {
//                if (systemFingerPrintDialog != null && RxFingerprint.isAvailable(this)) {
//                    if (BuildConfig.VERSION_CODE >= 23) {
//                        systemFingerPrintDialog.show();
//                    }
//                }
//                isOpenSettings = false;
//
//            } else {
//                isOpenSettings = true;
//                Tools.openNotificationDialog(this,
//                        getString(R.string.error_accessing_fingerprint_1),
//                        "Fingerprint Error", "Go to Biometric Transaction");
//            }
//        }
//
//        //This handles the 2FA with 3 Options for fingerprint
//        @Override
//        public void onFingerPrintClicked(String whichFragmentTag, String message) {
//            switch (whichFragmentTag) {
//                case "bua_pin_fingerprint":
//                case "bua_2FA_3options":
//                    //show the fingerprint dialog here
//                    showFingerPrintDialog();
//                    break;
//            }
//        }
//
//        @Override
//        public void onAuthSuccessful(String password, String deviceId) {
//            //this auth type value is deliberate for the backend
//            this.authType = AppConstants.AUTHENTICATION_TYPE_PIN;
//            this.secondFactorValue = password;
//
//            makeBuaPaymentRequest();
//        }
//
//        @Override
//        public void onAuthFailed(String error) {
//
//        }
//
//        @Override
//        public void onEnteredTokenOtp(String tokenOrOtp, boolean isToken) {
//            secondFactorValue = tokenOrOtp;
//            this.authType = isToken ? AppConstants.AUTHENTICATION_TYPE_TOKEN : AppConstants.AUTHENTICATION_TYPE_OTP;
//
//            makeBuaPaymentRequest();
//        }
//
//        @Override
//        public void onResendOtp() {
//            getViewModel().getOtp(senderAccount.getAccountNumber(), AppConstants.VALIDATE_FOR_TRANSACTION);
//        }
//    }
//
//
//
//
//    public class BuaActivityViewModel extends GetAccountsBaseViewModel {
//        private static final String TAG = "BuaActivityViewModel";
//        private final MutableLiveData<Resource<BuaPaymentResponse>> buaPaymentLiveData;
//        private final MutableLiveData<Resource<BuaProductTypeResponse>> buaAccountInfoLiveData;
//        private final MutableLiveData<Resource<BuaProductTypeResponse>> selectBuaProductTypeLiveData;
//        private final MutableLiveData<Resource<BuaCompanyResponse>> selectBuaCompanyLiveData;
//        private final MutableLiveData<Resource<List<AccountResponse>>> accountsResponseLiveData;
//        private final MutableLiveData<FeatureSchemeCode> featureSchemeCodesLiveData;
//        private final MutableLiveData<Resource<BuaCustomerOrderResponseModel>> validateCustomerOrderIdLiveData;
//        private final MutableLiveData<Resource<BankChargeResponse>> bankTransferCharge;
//        protected final SingleLiveEvent<Boolean> limitCheckForDialogLiveData;
//
//        private String currentCompanySelected;
//
//        public BuaActivityViewModel(RepositoryV2 repository, SchedulerProvider schedulerProvider) {
//            super(repository, schedulerProvider);
//            buaAccountInfoLiveData = new MutableLiveData<>();
//            selectBuaProductTypeLiveData = new MutableLiveData<>();
//            selectBuaCompanyLiveData = new MutableLiveData<>();
//            accountsResponseLiveData = new MutableLiveData<>();
//            featureSchemeCodesLiveData = new MutableLiveData<>();
//            validateCustomerOrderIdLiveData = new MutableLiveData<>();
//            limitCheckForDialogLiveData = new SingleLiveEvent<>();
//            buaPaymentLiveData = new MutableLiveData<>();
//            bankTransferCharge = new MutableLiveData<>();
//        }
//
//        public MutableLiveData<Resource<BuaProductTypeResponse>> getBuaProductsLiveData() {
//            return selectBuaProductTypeLiveData;
//        }
//
//        public MutableLiveData<Resource<BuaCompanyResponse>> getBuaCompanyLiveData() {
//            return selectBuaCompanyLiveData;
//        }
//
//        public MutableLiveData<FeatureSchemeCode> getFeatureSchemeCodesLiveData() {
//            return featureSchemeCodesLiveData;
//        }
//
//        public MutableLiveData<Resource<BuaCustomerOrderResponseModel>> getCustomerOrderIdLiveData() {
//            return validateCustomerOrderIdLiveData;
//        }
//
//        public MutableLiveData<Resource<BuaPaymentResponse>> getBuaPaymentLiveData() {
//            return buaPaymentLiveData;
//        }
//
//        public MutableLiveData<Boolean> get2FaLiveData() {
//            return limitCheckForDialogLiveData;
//        }
//
//        public void getFeatureSchemeCodes() {
//            featureSchemeCodesLiveData.setValue(getRepository().getFeatureSchemeCode());
//        }
//
//        public void do2FALimitCheckForDialogDisplay(long amount, boolean isQuickTransfer) {
//            if (isQuickTransfer) {
//                limitCheckForDialogLiveData.setValue(true);
//                return;
//            }
//            limitCheckForDialogLiveData.setValue(getRepository().hasReachedTwoFactorLimit(amount));
//        }
//
////    public MutableLiveData<Resource<BankChargeResponse>> getBankTransferCharge() {
////        return bankTransferCharge;
////    }
//
//        public void validateBuaCustomerOrderId(String orderId) {
//            getCompositeDisposable().add(getRepository()
//                    .executeValidateBuaOrderId(orderId)
//                    .doOnSubscribe(response -> validateCustomerOrderIdLiveData.postValue(Resource.loading()))
//                    .subscribeOn(getSchedulerProvider().io())
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribe(apiResponse -> {
//                        if (apiResponse.getSuccess()) {
//                            validateCustomerOrderIdLiveData.setValue(Resource.success(apiResponse.getResult()));
//                        } else {
//                            System.out.println("else block " + apiResponse.getApiErrors().get(0).getMessage());
//                            if (apiResponse.getApiErrors() != null) {
//                                ApiError apiError = apiResponse.getApiErrors().get(0);
//                                validateCustomerOrderIdLiveData.setValue(Resource.error(apiError.getMessage()));
//                            }
//                        }
//
//                    }, error -> validateCustomerOrderIdLiveData.setValue(Resource.error(getErrorMessage(error)))));
//        }
//
//        public void getBuaProductTypes(Integer id) {
//            getCompositeDisposable().add(getRepository()
//                    .executeBuaProductTypes(id)
//                    .doOnSubscribe(response -> selectBuaProductTypeLiveData.postValue(Resource.loading()))
//                    .subscribeOn(getSchedulerProvider().io())
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribe(response -> {
//                        if (response.getSuccess()) {
//                            if (response.getResult() != null) {
//                                selectBuaProductTypeLiveData.setValue(Resource.success(response.getResult()));
//                                //                          getRepository().setPrefBuaProducts(response.getResult());
//                                Log.d(TAG, "getBuaProductType: " + response.getResult());
//                            }
//                        } else {
//                            if (response.getApiErrors() != null) {
//                                ApiError apiError = response.getApiErrors().get(0);
//                                selectBuaProductTypeLiveData.setValue(Resource.error(apiError.getMessage()));
//                            }
//                        }
//                    }, error -> selectBuaProductTypeLiveData.setValue(Resource.error(getErrorMessage(error)))));
//        }
//
//        public void getBuaCompanies() {
//            getCompositeDisposable().add(getRepository()
//                    .executeGetBuaCompanies()
//                    .doOnSubscribe(response -> {
//                        selectBuaCompanyLiveData.postValue(Resource.loading());
//                    })
//                    .subscribeOn(getSchedulerProvider().io())
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribe(response -> {
//                        if (response.getSuccess()) {
//                            selectBuaCompanyLiveData.setValue(Resource.success(response.getResult()));
////                        getRepository().setPrefBuaCompany(response.getResult());
//                        }
//                    }, error -> selectBuaCompanyLiveData.setValue(Resource.error(getErrorMessage(error)))));
//        }
//
//        @Override
//        protected void onError(String errorMessage) {
//
//        }
//
//        public void executeBuaPayment(String secondFactor, long amount, String accountNumber, String pin,
//                                      int buaCompanyId, String buaCompanyName, String orderId, String clientId,
//                                      int productTypeId, String authType, String description, String deviceId) {
//            BuaPaymentRequest buaPaymentRequest = new BuaPaymentRequest();
//
//            buaPaymentRequest.setSecondFactor(secondFactor);
//            buaPaymentRequest.setRequestAmount(amount);
//            buaPaymentRequest.setCustomerAccount(accountNumber);
//            buaPaymentRequest.setPin(pin);
//            buaPaymentRequest.setProductTypeId(productTypeId);
//            buaPaymentRequest.setCompanyId(buaCompanyId);
//            buaPaymentRequest.setOrderId(orderId);
//            buaPaymentRequest.setClientId(clientId);
//            buaPaymentRequest.setDescription(description);
//            buaPaymentRequest.setAuthenticationType(authType);
//            buaPaymentRequest.setDeviceId(deviceId);
//
//            Log.d("BUA", buaPaymentRequest.toString());
//
//            getCompositeDisposable().add(getRepository()
//                    .executeBuaPayment(buaPaymentRequest, buaCompanyName)
//                    .doOnSubscribe(response -> {
//                        buaPaymentLiveData.postValue(Resource.loading());
//                    })
//                    .subscribeOn(getSchedulerProvider().io())
//                    .observeOn(getSchedulerProvider().ui())
//                    .subscribe(response -> {
//                        if (response.getSuccess() && response.getResult() != null) {
//                            buaPaymentLiveData.setValue(Resource.success(response.getResult()));
//                        }
//                    }, error -> buaPaymentLiveData.setValue(Resource.error(getErrorMessage(error)))));
//        }
//
//        public void executeGetTransferCharge(boolean isQuickTransfer) {
////        doGetBankCharges(bankTransferCharge, isQuickTransfer);
//
////i want the header to not go with auth token when calling it from quick transfer page so i save a flag here
//            getRepository().setIsQuickAction(isQuickTransfer);
//
//            getCompositeDisposable()
//                    .add(getRepository().executeGetAccountTransactionReceipt2()
//                            .subscribeOn(getSchedulerProvider().io())
//                            .observeOn(getSchedulerProvider().ui())
//                            .doOnSubscribe(loading -> bankTransferCharge.postValue(Resource.loading()))
//                            .subscribe(response -> {
//                                if (response.getSuccess()) {
//                                    bankTransferCharge.setValue(Resource.success(response.getResult()));
//                                } else {
//                                    if (response.getApiErrors() != null && response.getApiErrors().size() > 0) {
//                                        ApiError apiError = response.getApiErrors().get(0);
//                                        bankTransferCharge.setValue(Resource.error(apiError.getMessage()));
//
//                                    }
//                                }
//                            }, error -> {
//                                bankTransferCharge.setValue(Resource.error(getError(error)));
//                            })
//                    );
//
//            //bankTransferCharge.setValue(Resource.success(getRepository().getTransferChargersFromPref()));
//        }
//
//
//    }
//
//
//
//
// <com.appzonegroup.fcmb.features.cardmanagement.virtualcard.AccountListView
//    android:id="@+id/funding_acct"
//    android:layout_width="match_parent"
//    android:layout_height="wrap_content"
//    android:layout_marginTop="@dimen/padding_10" />
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
//}
//
//
//
//
//public class BuaCompany {
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    private final static long serialVersionUID = 3027842701039365075L;
//
//
//    public BuaCompany() {
//
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String toString() {
//        return name;
//    }
