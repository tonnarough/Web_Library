package by.epam.trainig.controller;

import by.epam.trainig.controller.command.CommandResponse;

import java.util.Objects;

public class WrappingCommandResponse implements CommandResponse {

    private final boolean redirect;
    private final String path;

    public WrappingCommandResponse(String path) {
        this(false, path);
    }

    public WrappingCommandResponse(boolean redirect, String path) {
        this.redirect = redirect;
        this.path = path;
    }

    @Override
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrappingCommandResponse that = (WrappingCommandResponse) o;
        return redirect == that.redirect && path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirect, path);
    }

    @Override
    public String toString() {
        return "WrappingCommandResponse{" +
                "redirect=" + redirect +
                ", path='" + path + '\'' +
                '}';
    }
}
