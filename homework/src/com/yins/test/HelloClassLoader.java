/**
 * @ClassName HelloClassLoader
 * @Description TODO
 * @Author yins
 * @Date 2021-3-16
 * @Version 1.0
 **/
package com.yins.test;

import java.util.Base64;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("com.yins.test.HelloWorld").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String helloBase64 = "yv66vgAAADMAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygp" +
                "VgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZp" +
                "bGUBAA9IZWxsb1dvcmxkLmphdmEMAAcACAcAFgwAFwAYAQALaGVsbG8gd29ybGQH" +
                "ABkMABoAGwEAGGNvbS95aW5zL3Rlc3QvSGVsbG9Xb3JsZAEAEGphdmEvbGFuZy9P" +
                "YmplY3QBABBqYXZhL2xhbmcvU3lzdGVtAQADb3V0AQAVTGphdmEvaW8vUHJpbnRT" +
                "dHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEv" +
                "bGFuZy9TdHJpbmc7KVYAIQAFAAYAAAAAAAIAAQAHAAgAAQAJAAAAHQABAAEAAAAF" +
                "KrcAAbEAAAABAAoAAAAGAAEAAAAKAAgACwAIAAEACQAAACUAAgAAAAAACbIAAhID" +
                "tgAEsQAAAAEACgAAAAoAAgAAAAwACAANAAEADAAAAAIADQ==";
        byte[] bytes = decode(helloBase64);
        return defineClass(name,bytes,0,bytes.length);
    }
    private static byte[] decode(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
