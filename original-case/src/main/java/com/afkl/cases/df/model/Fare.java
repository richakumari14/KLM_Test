package com.afkl.cases.df.model;

public class Fare {

	  private float amount;
	  private String currency;
	  private String origin;
	  private String destination;
	  private String totalamount;
		  
		public float getAmount() {
			return amount;
		}
		public void setAmount(float amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}
		
		
		public String getTotalamount() {
			return totalamount;
		}
		public void setTotalamount(String totalamount) {
			this.totalamount = totalamount;
		}
		@Override
		public String toString() {
			return "Fare [amount=" + amount + ", currency=" + currency + ", origin=" + origin + ", destination="
					+ destination + "]";
		}
		
		
	
}
