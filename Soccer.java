/**
 * Name: Wang Hongyanyan
 * Matric. No: A0239012R
 */
import java.util.*;

class AVLTree<Key extends Comparable<Key>, Value> {
  private Node root;

  private class Node {
    private final Key key;
    private Value val;
    private int height;
    private int size;
    private Node left;
    private Node right;

    public Node(Key key, Value val, int height, int size) {
      this.key = key;
      this.val = val;
      this.size = size;
      this.height = height;
    }
  }

  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null)
      return 0;
    return x.size;
  }

  private int height(Node x) {
    if (x == null)
      return -1;
    return x.height;
  }

  public Value get(Key key) {
    Node x = get(root, key);
    if (x == null)
      return null;
    return x.val;
  }

  private Node get(Node x, Key key) {
    if (x == null)
      return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0)
      return get(x.left, key);
    else if (cmp > 0)
      return get(x.right, key);
    else
      return x;
  }

  public boolean contains(Key key) {
    return get(key) != null;
  }

  public void put(Key key, Value val) {
    if (val == null) {
      delete(key);
      return;
    }
    root = put(root, key, val);
  }

  private Node put(Node x, Key key, Value val) {
    if (x == null)
      return new Node(key, val, 0, 1);
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = put(x.left, key, val);
    } else if (cmp > 0) {
      x.right = put(x.right, key, val);
    } else {
      x.val = val;
      return x;
    }
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node balance(Node x) {
    if (balanceFactor(x) < -1) {
      if (balanceFactor(x.right) > 0) {
        x.right = rotateRight(x.right);
      }
      x = rotateLeft(x);
    } else if (balanceFactor(x) > 1) {
      if (balanceFactor(x.left) < 0) {
        x.left = rotateLeft(x.left);
      }
      x = rotateRight(x);
    }
    return x;
  }

  private int balanceFactor(Node x) {
    return height(x.left) - height(x.right);
  }

  private Node rotateRight(Node x) {
    Node y = x.left;
    x.left = y.right;
    y.right = x;
    y.size = x.size;
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y;
  }

  private Node rotateLeft(Node x) {
    Node y = x.right;
    x.right = y.left;
    y.left = x;
    y.size = x.size;
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));
    return y;
  }

  public void delete(Key key) {
    if (!contains(key))
      return;
    root = delete(root, key);
  }

  private Node delete(Node x, Key key) {
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = delete(x.left, key);
    } else if (cmp > 0) {
      x.right = delete(x.right, key);
    } else {
      if (x.left == null) {
        return x.right;
      } else if (x.right == null) {
        return x.left;
      } else {
        Node y = x;
        x = min(y.right);
        x.right = deleteMin(y.right);
        x.left = y.left;
      }
    }
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node deleteMin(Node x) {
    if (x.left == null)
      return x.right;
    x.left = deleteMin(x.left);
    x.size = 1 + size(x.left) + size(x.right);
    x.height = 1 + Math.max(height(x.left), height(x.right));
    return balance(x);
  }

  private Node min(Node x) {
    if (x.left == null)
      return x;
    return min(x.left);
  }

  public int rank(Key key) {
    return 1 + rank(root, key);
  }
  private int rank(Node x, Key key) {
    if (x == null) {
      return 0;
    }
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      return rank(x.left, key);
    }
    else if (cmp > 0) {
      return 1+ size(x.left) +rank(x.right, key);
    }
    else {
      return size(x.left);
    }
  }
}

class Team implements Comparable<Team> {
  String name;
  Long score;
  int index;
  public Team(String name, Long score, int index) {
    this.score = score;
    this.name = name;
    this.index = index;
  }
  public int compareTo(Team t) {
    if (this.score < t.score) {
      return 1;
    } else if (this.score >t.score) {
      return -1;
    } else if (this.index < t.index) {
      return  -1;
    } else if (this.index > t.index) {
      return 1;
    } else {
      return 0;
    }
  }
}
@SuppressWarnings("unchecked")
public class Soccer {
  public static void main(String args[]) {
    Kattio io = new Kattio(System.in);
    AVLTree<Team, Integer> soccer = new AVLTree<Team, Integer>();
    HashMap<String, Team> map = new HashMap<String, Team>();
    int index = 0;
    int n = io.getInt();
    for (int i = 0; i<n; i++) {
      String cmd = io.getWord();
      if (cmd.equals("ADD")) {
        String name = io.getWord();
        Long score = io.getLong();
        Team t = new Team(name, score, index);
        add(soccer, map, t, name);
        index++;
      } else if (cmd.equals("MATCH")) {
        String t1 = io.getWord();
        String t2 = io.getWord();
        Long goal1 = io.getLong();
        Long goal2 = io.getLong();
        match(soccer, map, t1, t2, goal1, goal2);
      } else {
        String name = io.getWord();
        if (!map.containsKey(name)) {
          System.out.println("Team "+name+" is ELIMINATED");
        } else {
          Team t = (Team)map.get(name);
          Long score = t.score;
          int rank = query(soccer, map, name);
          System.out.println("Team "+name+": "+score+" points, rank "+rank);
        }
      }
    }
    io.close();
  }
  public static void add(AVLTree tree, HashMap map, Team team, String name) {
    tree.put(team, 0);
    map.put(name, team);
  }
  public static void match(AVLTree tree, HashMap map, String t1, String t2, Long goal1, Long goal2) {
    Long diff = Math.abs(goal1-goal2);
    Team team1 = (Team) map.get(t1);
    Team team2 = (Team) map.get(t2);
    Long s1 = team1.score;
    Long s2 = team2.score;
    if (goal1 < goal2) {
      tree.delete(team1);
      tree.delete(team2);
      team1.score = s1 - diff;
      team2.score = s2 + diff;
      add(tree, map, team2, t2);
      map.put(t2, team2);
      if (team1.score > 0) {
        add(tree, map, team1, t1);
        map.put(t1, team1);
      } else {
        map.remove(t1);
      }
    } else if (goal2<goal1) {
      tree.delete(team1);
      tree.delete(team2);
      team1.score = s1 + diff;
      team2.score = s2 - diff;
      add(tree, map, team1, t1);
      map.put(t1, team1);
      if (team2.score>0) {
        add(tree, map, team2, t2);
        map.put(t2, team2);
      } else {
        map.remove(t2);
      }
    }
  }
  public static int query(AVLTree tree, HashMap map, String name) {
    Team t = (Team) map.get(name);
    int k = tree.rank(t);
    return k;
  }
}
