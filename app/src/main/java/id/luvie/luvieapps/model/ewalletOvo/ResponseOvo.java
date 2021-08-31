package id.luvie.luvieapps.model.ewalletOvo;

import com.google.gson.annotations.SerializedName;

public class ResponseOvo{

	@SerializedName("voided_at")
	private Object voidedAt;

	@SerializedName("basket")
	private Object basket;

	@SerializedName("failure_code")
	private Object failureCode;

	@SerializedName("metadata")
	private Object metadata;

	@SerializedName("reference_id")
	private String referenceId;

	@SerializedName("created")
	private String created;

	@SerializedName("capture_now")
	private boolean captureNow;

	@SerializedName("is_redirect_required")
	private boolean isRedirectRequired;

	@SerializedName("channel_code")
	private String channelCode;

	@SerializedName("refunded_amount")
	private Object refundedAmount;

	@SerializedName("callback_url")
	private String callbackUrl;

	@SerializedName("payment_method_id")
	private Object paymentMethodId;

	@SerializedName("checkout_method")
	private String checkoutMethod;

	@SerializedName("currency")
	private String currency;

	@SerializedName("channel_properties")
	private ChannelProperties channelProperties;

	@SerializedName("void_status")
	private Object voidStatus;

	@SerializedName("id")
	private String id;

	@SerializedName("customer_id")
	private Object customerId;

	@SerializedName("business_id")
	private String businessId;

	@SerializedName("actions")
	private Object actions;

	@SerializedName("updated")
	private String updated;

	@SerializedName("status")
	private String status;

	@SerializedName("charge_amount")
	private int chargeAmount;

	@SerializedName("capture_amount")
	private int captureAmount;

	public void setVoidedAt(Object voidedAt){
		this.voidedAt = voidedAt;
	}

	public Object getVoidedAt(){
		return voidedAt;
	}

	public void setBasket(Object basket){
		this.basket = basket;
	}

	public Object getBasket(){
		return basket;
	}

	public void setFailureCode(Object failureCode){
		this.failureCode = failureCode;
	}

	public Object getFailureCode(){
		return failureCode;
	}

	public void setMetadata(Object metadata){
		this.metadata = metadata;
	}

	public Object getMetadata(){
		return metadata;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setCaptureNow(boolean captureNow){
		this.captureNow = captureNow;
	}

	public boolean isCaptureNow(){
		return captureNow;
	}

	public void setIsRedirectRequired(boolean isRedirectRequired){
		this.isRedirectRequired = isRedirectRequired;
	}

	public boolean isIsRedirectRequired(){
		return isRedirectRequired;
	}

	public void setChannelCode(String channelCode){
		this.channelCode = channelCode;
	}

	public String getChannelCode(){
		return channelCode;
	}

	public void setRefundedAmount(Object refundedAmount){
		this.refundedAmount = refundedAmount;
	}

	public Object getRefundedAmount(){
		return refundedAmount;
	}

	public void setCallbackUrl(String callbackUrl){
		this.callbackUrl = callbackUrl;
	}

	public String getCallbackUrl(){
		return callbackUrl;
	}

	public void setPaymentMethodId(Object paymentMethodId){
		this.paymentMethodId = paymentMethodId;
	}

	public Object getPaymentMethodId(){
		return paymentMethodId;
	}

	public void setCheckoutMethod(String checkoutMethod){
		this.checkoutMethod = checkoutMethod;
	}

	public String getCheckoutMethod(){
		return checkoutMethod;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setChannelProperties(ChannelProperties channelProperties){
		this.channelProperties = channelProperties;
	}

	public ChannelProperties getChannelProperties(){
		return channelProperties;
	}

	public void setVoidStatus(Object voidStatus){
		this.voidStatus = voidStatus;
	}

	public Object getVoidStatus(){
		return voidStatus;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCustomerId(Object customerId){
		this.customerId = customerId;
	}

	public Object getCustomerId(){
		return customerId;
	}

	public void setBusinessId(String businessId){
		this.businessId = businessId;
	}

	public String getBusinessId(){
		return businessId;
	}

	public void setActions(Object actions){
		this.actions = actions;
	}

	public Object getActions(){
		return actions;
	}

	public void setUpdated(String updated){
		this.updated = updated;
	}

	public String getUpdated(){
		return updated;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setChargeAmount(int chargeAmount){
		this.chargeAmount = chargeAmount;
	}

	public int getChargeAmount(){
		return chargeAmount;
	}

	public void setCaptureAmount(int captureAmount){
		this.captureAmount = captureAmount;
	}

	public int getCaptureAmount(){
		return captureAmount;
	}

	@Override
 	public String toString(){
		return 
			"ResponseOvo{" + 
			"voided_at = '" + voidedAt + '\'' + 
			",basket = '" + basket + '\'' + 
			",failure_code = '" + failureCode + '\'' + 
			",metadata = '" + metadata + '\'' + 
			",reference_id = '" + referenceId + '\'' + 
			",created = '" + created + '\'' + 
			",capture_now = '" + captureNow + '\'' + 
			",is_redirect_required = '" + isRedirectRequired + '\'' + 
			",channel_code = '" + channelCode + '\'' + 
			",refunded_amount = '" + refundedAmount + '\'' + 
			",callback_url = '" + callbackUrl + '\'' + 
			",payment_method_id = '" + paymentMethodId + '\'' + 
			",checkout_method = '" + checkoutMethod + '\'' + 
			",currency = '" + currency + '\'' + 
			",channel_properties = '" + channelProperties + '\'' + 
			",void_status = '" + voidStatus + '\'' + 
			",id = '" + id + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",business_id = '" + businessId + '\'' + 
			",actions = '" + actions + '\'' + 
			",updated = '" + updated + '\'' + 
			",status = '" + status + '\'' + 
			",charge_amount = '" + chargeAmount + '\'' + 
			",capture_amount = '" + captureAmount + '\'' + 
			"}";
		}
}