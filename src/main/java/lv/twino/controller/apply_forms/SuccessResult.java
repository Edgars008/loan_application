package lv.twino.controller.apply_forms;

public class SuccessResult<T> extends ApplyResult {
    private final T success;

    public SuccessResult(T success) {
        this.success = success;
    }

    public T getMessage() {
        return success;
    }
}
