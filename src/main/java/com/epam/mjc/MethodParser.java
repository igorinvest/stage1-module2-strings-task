package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MethodParser {
    static Logger log = Logger.getLogger(MethodParser.class.getName());
    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier = null;
        String returnType;
        String methodName;
        String[] arguments;

        int aIndexStart = signatureString.indexOf('(');
        int aIndexEnd = signatureString.indexOf(')');
        arguments = signatureString.substring(aIndexStart + 1, aIndexEnd).split(", ");

        List<MethodSignature.Argument> args = new ArrayList<>();
        try{
            for (int i = 0; i < arguments.length; i++) {
                String[] splitted = arguments[i].split(" ");
                MethodSignature.Argument a = new MethodSignature.Argument(splitted[0], splitted[1]);
                args.add(a);
            }
        } catch (Exception e) {
            log.info(e.toString());
        }

        String[] withoutArguments = signatureString.substring(0, aIndexStart).split(" ");
        if(withoutArguments[0].equals("private") || withoutArguments[0].equals("public")) {
            accessModifier = withoutArguments[0];
        }

        if(accessModifier == null) {
            returnType = withoutArguments[0];
        } else {
            returnType = withoutArguments[1];
        }


        if(accessModifier == null) {
            methodName = withoutArguments[1];
        } else {
            methodName = withoutArguments[2];
        }

        MethodSignature ms = new MethodSignature(methodName, args);
        ms.setAccessModifier(accessModifier);
        ms.setReturnType(returnType);
        return ms;
    }
}
