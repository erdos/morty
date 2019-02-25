package io.github.erdos.stencil.standalone;

import java.io.File;
import java.util.Optional;

@SuppressWarnings("WeakerAccess")
public final class StencilArgsParser {

    public final static ArgsParser PARSER = new ArgsParser();

    /**
     * Result files are placed in this directory.
     */
    public static final ArgsParser.ParamMarker<File> OUTPUT_DIR = PARSER.addParam('o', "output", "Output directory path", x -> {
        final File output = new File(x);
        if (!output.exists()) {
            throw new IllegalArgumentException("Output directory does not exist: " + output);
        } else if (!output.isDirectory()) {
            throw new IllegalArgumentException("Output directory parameter is not a directory: " + output);
        } else {
            return output;
        }
    });

    /**
     * Result files will be overwritten when already exist in target directory.
     */
    public static final ArgsParser.ParamMarker<Boolean> OVERWRITE = PARSER.addFlagOption('O', "overwrite", "Overwrite existing files");

    public static ArgsParser.ParseResult parse(String... args) {
        return PARSER.parse(args);
    }

    /**
     * Finds output directory in parsed parameters or returns current working directory.
     *
     * @return output directory where rendered files will be put
     * @throws NullPointerException     if param is null
     * @throws IllegalArgumentException if output file path does not exist or is not a directory
     */
    public static File getOutputDirectory(ArgsParser.ParseResult result) {
        final Optional<File> maybeDir = result.getParamValue(OUTPUT_DIR);
        final File f = maybeDir.orElse(new File(".").getAbsoluteFile());

        if (!f.exists()) {
            throw new IllegalArgumentException("Output directory does not exist: " + f);
        } else if (!f.isDirectory()) {
            throw new IllegalArgumentException("Output path is not a directory: " + f);
        } else {
            return f;
        }
    }

    /**
     * Finds output directory in parsed parameters or returns current working directory.
     *
     * @return should resulting files override existing files?
     * @throws NullPointerException if param is null
     */
    public static Boolean getOutputOverwritten(ArgsParser.ParseResult result) {
        return result.getParamValue(OVERWRITE).orElse(false);
    }
}
