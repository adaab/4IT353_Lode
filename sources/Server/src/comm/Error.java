package comm;

/*
třída error pro chyby v logice aplikace - chyba při přihlašování, chyba při vyhodnocování zásahu apod.
 */

import java.io.Serializable;

public class Error implements Serializable {
    public enum Code{
        userExists,
        fieldNotExists,
        fieldAlreadyHit;
        //co bude dál potřeba
    };
    public Code type;
    public String descr;

    public Error(Code type, String descr) {
        this.type = type;
        this.descr = descr;
    }
}
