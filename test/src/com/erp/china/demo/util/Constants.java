package com.erp.china.demo.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Robby B. Setyo
 * 
 * Description: constants variables declaration
 */
public class Constants {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	public static final SimpleDateFormat invoice_sdf = new SimpleDateFormat("yyyy/MMM/dd", Locale.US);

	public static final String SELECTED = "selected";
	public static final String CUST_TYPE = "custType";
	public static final String PAYMENT_TYPE = "paymentType";

	public static final String DEFAULT_SYSTEM_ID = "100";

	public static final String LANGUAGE_EN = "English";
	public static final String LANGUAGE_ZH_HK = "Traditional Chinese";
	public static final String LANGUAGE_ZH_CN = "Simplified Chinese";

	public static final String CREATED_DATE = "createdDate";
	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";

	public static final String ORDER_NO = "ORDER_NO";
	public static final String CUSTOMER_PO_NO = "CUSTOMER_PO_NO";
	public static final String CUSTOMER_NO = "CUSTOMER_NO";
	public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
	public static final String CUSTOMER_ADDRESS = "CUSTOMER_ADDRESS";
	public static final String CUSTOMER_CONTACT = "CUSTOMER_CONTACT";
	public static final String CUSTOMER_PHONE = "CUSTOMER_PHONE";
	public static final String ORDER_DATE = "ORDER_DATE";
	public static final String DELIVERY_DATE = "DELIVERY_DATE";
	public static final String SALES_NAME = "SALES_NAME";
	public static final String REMARKS = "REMARKS";

	public static final String PRODUCT_CODE = "PRODUCT_CODE";
	public static final String PRODUCT_NAME = "PRODUCT_NAME";
	public static final String PRODUCT_DESC = "PRODUCT_DESC";
	public static final String PRODUCT_YEAR = "PRODUCT_YEAR";
	public static final String UNIT_PRICE = "UNIT_PRICE";
	public static final String DISCOUNT = "DISCOUNT";
	public static final String PRODUCT_PRICE = "PRODUCT_PRICE";
	public static final String BOOKING_QTY = "BOOKING_QTY";
	public static final String TOTAL_ITEM_AMOUNT = "TOTAL_ITEM_AMOUNT";
	public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";
}