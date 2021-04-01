package ceHash;

import edu.princeton.cs.algs4.LinearProbingHashST;

public class Predictor {
    private String context;
    private LinearProbingHashST<String, MoveCounter> contextMap;



    public Predictor() {
        MoveCounter mc = new MoveCounter();
        context = "****"; // *RLR, LRRL, **RL
        contextMap.put(context, mc);

    }

    public Move predict() {
        if(contextMap.contains(context)) {
            MoveCounter moveCounter = contextMap.get(context);
            if (moveCounter.left() > moveCounter.right()) {
                return Move.LEFT;
            } else if (moveCounter.left() < moveCounter.right()) {
                return Move.RIGHT;
            } else if (moveCounter.left() == moveCounter.right()) {
                if(moveCounter.left == moveCounter.right) {
                    int rand = (int) (Math.random() * 2);
                    if (rand == 0) {
                        return Move.LEFT;
                    } else {
                        return Move.RIGHT;
                    }
                } else if(moveCounter.right > moveCounter.left) {
                    return Move.LEFT;
                } else {
                    return Move.RIGHT;
                }
            } else {
                throw new NullPointerException();
            }

        }

        return null;

    }

     public void recordMove(Move m) {
        MoveCounter mc = new MoveCounter();

        if(!context.contains("*")) {
            if(contextMap.contains(context)) {
                mc.increment(m);

            } else {
                contextMap.put(context, mc);
            }
        }

     }

     public static void main(String args[]) {


     }

}
