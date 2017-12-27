package co.omise.generators;

import java.io.IOException;

public abstract class Generator {
    abstract void generate(String rootPath) throws IOException;
}
