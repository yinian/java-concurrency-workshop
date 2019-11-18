package com.leonardoz.patterns.fixed_lock_ordering;

/**
 * Pattern: Fixed Lock Ordering
 *
 * Motivations: Acquiring locks in a non fixed-order can deadlock if they're called at the
 * same time, but with the inverse order.
 *
 * Intent: Create a fixed-ordered locking mechanism to prevent possibles
 * deadlocks. We define a value to the locks objects and use comparisons to
 * establish a fixed order bases on who is greater or lesser.
 *
 * Applicability: Every time when acquiring more than one lock.
 *
 * @Auther: viagra
 * @Date: 2019/11/14 09:15
 * @Description:
 */
public class FixedLockOrdering {



    static class LockableObject {
        private int id;
        private String anotherValue;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAnotherValue() {
            return anotherValue;
        }

        public void setAnotherValue(String anotherValue) {
            this.anotherValue = anotherValue;
        }

    }

    public void doSomeOperation(LockableObject obj1, LockableObject obj2) {
        int obj1Id = obj1.getId();
        int obj2Id = obj2.getId();
        if (obj1Id < obj2Id) {
            synchronized (obj1) {
                synchronized (obj2) {
                    // action
                }
            }
        } else {
            synchronized (obj2) {
                synchronized (obj1) {
                    // action
                }
            }
        }
    }
}
