package mipt.kotlin.project
import kotlin.test.Test
import kotlin.test.assertTrue

class bernoulliRandomTest {
    @Test fun bernoulliRandomTest(){
        assertTrue((bernoulliRandomList(0.9, 100.0, 1000).sum()/1000.0)>0.7)
        assertTrue((bernoulliRandomList(0.2, 100.0, 1000).sum()/1000.0)<0.5)
    }
}
