package com.project.errors;

public class AppExceptionStrings {

    // general
    public static final String NOT_FOUND = "Failed to find the requested item";
    public static final String NOT_FOUND_BY_ID = "Failed to find the requested item with id: {0}";
    public static final String UNPROCESSED_INPUT_ARGS = "Invalid input arguments";

    // Gate
    public static final String GATES_NOT_AVAILABLE = "There is no available gate";
    public static final String GATE_NOT_AVAILABLE = "Gate {0} is not available";
    public static final String GATE_ALREADY_EXISTS = "Gate {0} already exists";

    // Flight
    public static final String GATE_ALREADY_AVAILABLE = "Gate: {0} is already available";
}
