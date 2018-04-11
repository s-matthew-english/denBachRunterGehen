package com.smatthewenglish;

import java.util.Objects;

/**
 * Kademlia node. Contains an ID and a link.
 * <p>
 * Class is immutable.
 * @author Kasra Faghihi
 */
public final class Node {
  private static final long serialVersionUID = 1L;

  private final Id id;
  private final String link;

  /**
   * Constructs a {@link Node} object.
   * @param id ID of node
   * @param link link of node
   * @throws NullPointerException if any argument is {@code null}
   */
  public Node(Id id, String link) {
    this.id = id;
    this.link = link;
  }

  /**
   * Get this node's ID.
   * @return ID
   */
  public Id getId() {
    return id;
  }

  /**
   * Get this node's link.
   * @return link
   */
  public String getLink() {
    return link;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 37 * hash + Objects.hashCode(this.id);
    hash = 37 * hash + Objects.hashCode(this.link);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Node other = (Node) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.link, other.link)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Node{" + "id=" + id + ", link=" + link + '}';
  }

}
