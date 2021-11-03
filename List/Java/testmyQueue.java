package LIST;

public class testmyQueue {
    public static void main(String[] args){
        MyArrayQueue<Integer> testArrayQueue=new MyArrayQueue<>();

        int n=1000;
        long testAQenQueueST=System.nanoTime();
        for(int i=0;i<n;i++){
            testArrayQueue.enQueue(i);
        }
        long testAQenQueueET=System.nanoTime();
        long testAQenQueueT=testAQenQueueET-testAQenQueueST;
        System.out.println("测试"+n+"次myArrayQueue的enQueue操作的运行时间为 "+testAQenQueueT+"ns");
        long testAQdeQueueST=System.nanoTime();
        for(int i=0;i<n;i++){
            testArrayQueue.deQueue();
        }
        long testAQdeQueueET=System.nanoTime();
        long testAQdeQueueT=testAQdeQueueET-testAQdeQueueST;
        System.out.println("测试"+n+"次myArrayQueue的deQueue操作的运行时间为 "+testAQdeQueueT+"ns");

        MyLinkedQueue<Integer> testLinkedQueue=new MyLinkedQueue<>();
        long testLQenQueueST=System.nanoTime();
        for(int i=0;i<n;i++){
            testLinkedQueue.enQueue(i);
        }
        long testLQenQueueET=System.nanoTime();
        long testLQenQueueT=testLQenQueueET-testLQenQueueST;
        System.out.println("测试"+n+"次myLinkedQueue的enQueue操作的运行时间为 "+testLQenQueueT+"ns");
        long testLQdeQueueST=System.nanoTime();
        for(int i=0;i<n;i++){
            testLinkedQueue.deQueue();
        }
        long testLQdeQueueET=System.nanoTime();
        long testLQdeQueueT=testLQdeQueueET-testLQdeQueueST;
        System.out.println("测试"+n+"次myLinkedQueue的deQueue操作的运行时间为 "+testLQdeQueueT+"ns");
    }
}
