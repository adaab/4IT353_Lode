package comm;

/*
třída error pro chyby v logice aplikace - chyba při přihlašování, chyba při vyhodnocování zásahu apod.
 */

public class Error {
    public enum code{
        userExists,
        fieldNotExists,
        fieldAlreadyHit;
        //co bude dál potřeba
    };
    public String descr;


}
