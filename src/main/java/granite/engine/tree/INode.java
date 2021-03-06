package granite.engine.tree;

import java.util.Collection;

public interface INode<T extends INode<T>> {

    INode<T> getParent();

    void setParent(INode<T> parent);

    Collection<T> getChildren();

    void addChild(T child);

    void removeChild(T child);

    Collection<T> getDescendants();

    void addDescendants(Collection<T> descendants);

    void removeDescendants(Collection<T> descendants);
}
