package LIST;

public class testmyStack {
    public static void main(String[] args){
        MyArrayStack<Integer> testArrayStack=new MyArrayStack<>();
        MyLinkedStack<Integer> testLinkedStack=new MyLinkedStack<>();

        int n=1000;
        long testASPushST=System.nanoTime();
        for(int i=0;i<n;i++){
            testArrayStack.push(i);
        }
        long testASPushET=System.nanoTime();
        long testASPushT=testASPushET-testASPushST;
        System.out.println("测试"+n+"次myArrayStack的push操作的运行时间为 "+testASPushT+"ns");
        long testASTopST=System.nanoTime();
        for(int i=0;i<n;i++){
            testArrayStack.top();
        }
        long testASTopET=System.nanoTime();
        long testASTopT=testASTopET-testASTopST;
        System.out.println("测试"+n+"次myArrayStack的top操作的运行时间为 "+testASTopT+"ns");
        long testASPopST=System.nanoTime();
        for(int i=0;i<n;i++){
            testArrayStack.pop();
        }
        long testASPopET=System.nanoTime();
        long testASPopT=testASPopET-testASPopST;
        System.out.println("测试"+n+"次myArrayStack的pop操作的运行时间为 "+testASPopT+"ns");

        long testLSPushST=System.nanoTime();
        for(int i=0;i<n;i++){
            testLinkedStack.push(i);
        }
        long testLSPushET=System.nanoTime();
        long testLSPushT=testLSPushET-testLSPushST;
        System.out.println("测试"+n+"次myLinkedStack的push操作的运行时间为 "+testLSPushT+"ns");
        long testLSTopST=System.nanoTime();
        for(int i=0;i<n;i++){
            testLinkedStack.top();
        }
        long testLSTopET=System.nanoTime();
        long testLSTopT=testLSTopET-testLSTopST;
        System.out.println("测试"+n+"次myLinkedStack的top操作的运行时间为 "+testLSTopT+"ns");
        long testLSPopST=System.nanoTime();
        for(int i=0;i<n;i++){
            testLinkedStack.pop();
        }
        long testLSPopET=System.nanoTime();
        long testLSPopT=testLSPopET-testLSPopST;
        System.out.println("测试"+n+"次myLinkedStack的pop操作的运行时间为 "+testLSPopT+"ns");
    }
}
