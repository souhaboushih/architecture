package com.example.ecommerceapp.adapters.rest.dashboard;

public class DashboardDTO {

    private long totalOrders;
    private long paidOrders;
    private long unpaidOrders;
    private double totalRevenue;

    public DashboardDTO(long totalOrders, long paidOrders, long unpaidOrders, double totalRevenue) {
        this.totalOrders = totalOrders;
        this.paidOrders = paidOrders;
        this.unpaidOrders = unpaidOrders;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalOrders() { return totalOrders; }
    public long getPaidOrders() { return paidOrders; }
    public long getUnpaidOrders() { return unpaidOrders; }
    public double getTotalRevenue() { return totalRevenue; }

    public void setTotalOrders(long totalOrders) { this.totalOrders = totalOrders; }
    public void setPaidOrders(long paidOrders) { this.paidOrders = paidOrders; }
    public void setUnpaidOrders(long unpaidOrders) { this.unpaidOrders = unpaidOrders; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
}
