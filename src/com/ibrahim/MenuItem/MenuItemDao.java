package com.ibrahim.MenuItem;




public class MenuItemDao {

    private static final MenuItem[] menuItems;

    static {
        menuItems = new MenuItem[]{
            new MenuItem(1, "Book Car"),
                new MenuItem(2, "View All User Booked Cars"),
                new MenuItem(3, "View All Bookings"),
                new MenuItem(4, "View Available Cars"),
                new MenuItem(5, "View Available Electric Cars"),
                new MenuItem(6, "View all users"),
                new MenuItem(7, "Exit"),

        };
    }

    public static MenuItem[] getMenuItems() {
        return menuItems;
    }

}