package org.refresher.utils;

import org.refresher.models.Parking;
import org.refresher.models.Priority;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

public class PriorityParkingComparator implements Comparator<Parking> {
    private AtomicBoolean priorityset = new AtomicBoolean(false);

    public void setPriority(Priority priority) {
        if (priority == Priority.PRIORITIZED) {
            this.priorityset.set(true);
        } else {
            this.priorityset.set(false);
        }
    }

    @Override
    public int compare(Parking o1, Parking o2) {
        if (priorityset.get() == true) {
            if (o1.parkingNo < o2.parkingNo && o1.parkingNo < 10) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (o1.parkingNo < o2.parkingNo) {
                return -1;
            } else {
                return 1;
            }
        }

        /* if(o1.parkingNo > o2.parkingNo) {
            return 1;
        } else {
            return -1;
        }*/
    }
}
