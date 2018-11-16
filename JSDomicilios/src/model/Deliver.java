package model;

public class Deliver {
	
	private String client;
	private int[] address;
	
	public Deliver(String client, int[] address) {
		this.client = client;
		this.address = address;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int[] getAddress() {
		return address;
	}

	public void setAddress(int[] address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return  client;
	}
	
	

}
