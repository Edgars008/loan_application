package lv.twino.controller.apply_forms;

public class ErrorResult extends ApplyResult {

    private final String error;

    public ErrorResult(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
