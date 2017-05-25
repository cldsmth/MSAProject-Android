package com.android.msaproject.ui.dashboard;

public interface DashboardView {
    /**
     * Pre Checkin
     */
    void onPreCheckIn();
    /**
     * Checkin
     */
    void onCheckin();
    /**
     * Checkin Exist
     */
    void onCheckinExist();
    /**
     * Checkout
     */
    void onCheckout();
}