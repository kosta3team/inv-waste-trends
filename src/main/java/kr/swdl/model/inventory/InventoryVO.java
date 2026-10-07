package kr.swdl.model.inventory;

public class InventoryVO {

	private String fruitNo; // 과일 상품일련번호
	private String productName; // 상품명
	private int remainQuantity; // 재고수량
	private int price; // 단가
	private String coopName; // 협동조합원명
	private String storageDate; // 보관일자
	private String wasteDate; //폐기일자(처리)
	private String wasteCategoryReason; // 폐기사유
	private String wasteReasonDetail; // 폐기상세사유
	private String status; //폐기상태


	private String itemCode; // 품목코드
	private String itemName; // 품목명
	private String kindName; // 품종
	private String origin; // 원산지
	private double weight; // 중량
	private int inventoryQuantity; // 입고수량
	private String inventoryMemberName; // 입고요청자
	private String inventoryAdminName; // 입고등록자
	private String inventoryDate; // 입고일자
	private String wasteAdminName; // 폐기등록자
	private String wasteMemberName; // 폐기요청자
	private int wasteQuantity; // 폐기수량
	private String wasteReqDate; // 폐기요청일자
	private double dailyRainfall; // 일강수량
	private double maxTemp; // 최고기온
	private double avgTemp; // 평균기온
	private double minTemp; // 최저기온

	private String fruitCategoryNo; 

	public InventoryVO (){}



	public String getFruitCategoryNo() {
		return fruitCategoryNo;
	}





	public void setFruitCategoryNo(String fruitCategoryNo) {
		this.fruitCategoryNo = fruitCategoryNo;
	}


	public InventoryVO(String fruitNo, String productName, int remainQuantity, int price, String coopName,
			String storageDate, String wasteDate, String wasteCategoryReason, String status) {
		setFruitNo(fruitNo);
		setProductName(productName);
		setRemainQuantity(remainQuantity);
		setPrice(price);
		setCoopName(coopName);
		setStorageDate(storageDate);
		setWasteDate(wasteDate);
		setWasteCategoryReason(wasteCategoryReason);
		setStatus(status);

	}




	public InventoryVO(String fruitCategoryNo,String itemCode, String kindName, String origin, String itemName, String productName, int price,
			double weight,String inventoryDate, int inventoryQuantity, int remainQuantity,String coopName, String inventoryMemberName, String inventoryAdminName,
			String storageDate, int wasteQuantity, String wasteReqDate, String wasteDate, String wasteReasonDetail,  
			double dailyRainfall, double maxTemp, double avgTemp, double minTemp, String wasteMemberName, String wasteAdminName
			) {
		setFruitCategoryNo(fruitCategoryNo);
		setItemCode(itemCode);
		setKindName(kindName);
		setOrigin(origin);
		setItemName(itemName);
		setProductName(productName);
		setPrice(price);
		setWeight(weight);
		setInventoryDate(inventoryDate);
		setInventoryQuantity(inventoryQuantity);
		setRemainQuantity(remainQuantity);
		
		setCoopName(coopName);
		setInventoryMemberName(inventoryMemberName);
		setInventoryAdminName(inventoryAdminName);
		setStorageDate(storageDate);
		setWasteQuantity(wasteQuantity);
		setWasteReqDate(wasteReqDate);
		setWasteDate(wasteDate);
		setWasteReasonDetail(wasteReasonDetail);
		setDailyRainfall(dailyRainfall);
		setMaxTemp(maxTemp);
		setAvgTemp(avgTemp);
		setMinTemp(minTemp);
		setWasteMemberName(wasteMemberName);
		setWasteAdminName(wasteAdminName);

		
	}



	public String getFruitNo() {
		return fruitNo;
	}


	public void setFruitNo(String fruitNo) {
		this.fruitNo = fruitNo;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getRemainQuantity() {
		return remainQuantity;
	}


	public void setRemainQuantity(int remainQuantity) {
		this.remainQuantity = remainQuantity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getCoopName() {
		return coopName;
	}


	public void setCoopName(String coopName) {
		this.coopName = coopName;
	}


	public String getStorageDate() {
		return storageDate;
	}


	public void setStorageDate(String storageDate) {
		this.storageDate = storageDate;
	}


	public String getWasteDate() {
		return wasteDate;
	}


	public void setWasteDate(String wasteDate) {
		this.wasteDate = wasteDate;
	}


	public String getWasteCategoryReason() {
		return wasteCategoryReason;
	}


	public void setWasteCategoryReason(String wasteCategoryReason) {
		this.wasteCategoryReason = wasteCategoryReason;
	}


	public String getWasteReasonDetail() {
		return wasteReasonDetail;
	}


	public void setWasteReasonDetail(String wasteReasonDetail) {
		this.wasteReasonDetail = wasteReasonDetail;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getKindName() {
		return kindName;
	}


	public void setKindName(String kindName) {
		this.kindName = kindName;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public int getInventoryQuantity() {
		return inventoryQuantity;
	}


	public void setInventoryQuantity(int inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}


	public String getInventoryMemberName() {
		return inventoryMemberName;
	}


	public void setInventoryMemberName(String inventoryMemberName) {
		this.inventoryMemberName = inventoryMemberName;
	}


	public String getInventoryAdminName() {
		return inventoryAdminName;
	}


	public void setInventoryAdminName(String inventoryAdminName) {
		this.inventoryAdminName = inventoryAdminName;
	}


	public String getInventoryDate() {
		return inventoryDate;
	}


	public void setInventoryDate(String inventoryDate) {
		this.inventoryDate = inventoryDate;
	}


	public String getWasteAdminName() {
		return wasteAdminName;
	}


	public void setWasteAdminName(String wasteAdminName) {
		this.wasteAdminName = wasteAdminName;
	}


	public String getWasteMemberName() {
		return wasteMemberName;
	}


	public void setWasteMemberName(String wasteMemberName) {
		this.wasteMemberName = wasteMemberName;
	}


	public int getWasteQuantity() {
		return wasteQuantity;
	}


	public void setWasteQuantity(int wasteQuantity) {
		this.wasteQuantity = wasteQuantity;
	}


	public String getWasteReqDate() {
		return wasteReqDate;
	}


	public void setWasteReqDate(String wasteReqDate) {
		this.wasteReqDate = wasteReqDate;
	}


	public double getDailyRainfall() {
		return dailyRainfall;
	}


	public void setDailyRainfall(double dailyRainfall) {
		this.dailyRainfall = dailyRainfall;
	}


	public double getMaxTemp() {
		return maxTemp;
	}


	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}


	public double getAvgTemp() {
		return avgTemp;
	}


	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}


	public double getMinTemp() {
		return minTemp;
	}


	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}






}
