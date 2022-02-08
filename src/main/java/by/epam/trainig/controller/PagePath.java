package by.epam.trainig.controller;

public enum PagePath {

    LOGIN("WEB-INF/jsp/login.jsp"),
    REGISTRATION("/WEB-INF/jsp/registration.jsp"),
    SUBSCRIPTION("/WEB-INF/jsp/subscription.jsp"),
    MAIN_UNAUTH("/WEB-INF/jsp/main_unauth.jsp"),
    MAIN_AUTH("/WEB-INF/jsp/main_auth.jsp"),
    UPDATE_BOOK("/WEB-INF/jsp/update_book.jsp"),
    SELECTED_BOOK("/WEB-INF/jsp/selected_book.jsp"),
    SEARCH_BOOK("/WEB-INF/jsp/search_book.jsp"),
    ADDING_BOOK("/WEB-INF/jsp/book_adding.jsp"),
    INDEX("index.jsp");

    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    public static PagePath of(String name){
        for (PagePath page : values()){
            if (page.name().equalsIgnoreCase(name)){
                return page;
            }
        }
        return MAIN_UNAUTH;
    }


}
