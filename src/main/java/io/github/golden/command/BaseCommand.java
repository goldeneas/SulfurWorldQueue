package io.github.golden.command;

public abstract class BaseCommand {

    // todo: check how many args are actually required

    private String[] args;

    protected BaseCommand(String... args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    // events
    public abstract void onCommand();
    public abstract void onNotEnoughArguments();
    public abstract void onNotEnoughPermissions();

}
