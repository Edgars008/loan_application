package lv.twino.controller.apply_forms;

public class SuccessResult<T> extends ApplyResult {
    private final T value;

    public SuccessResult(T value) {
        this.value = value;
    }

    public T getMessage() {
        return value;
    }
}
