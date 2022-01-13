package by.epam.trainig.controller;

public enum PagePath {

    LOGIN("WEB-INF/jsp/login.jsp"),
    REGISTRATION("/WEB-INF/jsp/registration.jsp"),
    MAIN_UNAUTH("/WEB-INF/jsp/main_unauth.jsp"),
    MAIN_AUTH("/WEB-INF/jsp/main_auth.jsp");

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
