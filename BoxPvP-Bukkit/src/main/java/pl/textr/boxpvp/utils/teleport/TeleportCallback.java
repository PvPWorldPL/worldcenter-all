package pl.textr.boxpvp.utils.teleport;

public interface TeleportCallback<E>
{
    void success(final E p0);
    
    void error(final E p0);
}
