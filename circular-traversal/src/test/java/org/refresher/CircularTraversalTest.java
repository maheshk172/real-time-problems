package org.refresher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CircularTraversalTest {

    @Ignore
    @Test
    public void test_WhenITraverserCircularListWeNeedToFindLastSurvivor(){
        CircularTraversal service = new CircularLinkedListTraversal();
        Assert.assertNotNull(service);
        runTestScenariosOfLastSurvival(service);
    }

    @Test
    public void test_WhenITraverserCircularListWeNeedToFindLastSurvivorWithArray(){
        CircularArrayTraversal service = new CircularArrayTraversal();
        Assert.assertNotNull(service);
        runTestScenariosOfLastSurvival(service);
    }

    public void runTestScenariosOfLastSurvival(CircularTraversal service) {
        //int[] elements = {1,2,3,4,5,6,7,8};
        //Assert.assertEquals(1, service.removeNextAndGiveKnifeToNext(elements));

        int[] elements1 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        Assert.assertEquals(11, service.removeNextAndGiveKnifeToNext(elements1));

        //int[] elements2 = {1,2,3,4,5,6,7};
        //Assert.assertEquals(7, service.removeNextAndGiveKnifeToNext(elements2));

    }

}
