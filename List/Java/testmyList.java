package LIST;

public class testmyList {
    public static void main (String[] args){
        MyArrayList<Integer> testmyArrayList=new MyArrayList<>();
        long testALAddST=System.nanoTime();
        for(int i=0;i<1000;i++){    //增1000项
            testmyArrayList.add((int)(Math.random()*testmyArrayList.size()),i);
        }
        long testALAddET=System.nanoTime();
        long testALAddT=testALAddET-testALAddST;
        System.out.println("测试1000次myArrayList的add操作的运行时间为 "+testALAddT+"ns");

        long testALGetST=System.nanoTime();
        for(int i=0;i<1000;i++){   //查1000项
            testmyArrayList.get(i);
        }
        long testALGetET=System.nanoTime();
        long testALGetT=testALGetET-testALGetST;
        System.out.println("测试1000次myArrayList的get操作的运行时间为 "+testALGetT+"ns");

        long testALSetST=System.nanoTime();
        for(int i=0;i<1000;i++){   //改1000项
            testmyArrayList.set(i,0);
        }
        long testALSetET=System.nanoTime();
        long testALSetT=testALSetET-testALSetST;
        System.out.println("测试1000次myArrayList的set操作的运行时间为 "+testALSetT+"ns");

        long testALRemoveST=System.nanoTime();
        for(int i=0;i<1000;i++){    //删1000项
            testmyArrayList.remove((int)(Math.random()*testmyArrayList.size()));
        }
        long testALRemoveET=System.nanoTime();
        long testALRemoveT=testALRemoveET-testALRemoveST;
        System.out.println("测试1000次myArrayList的remove操作的运行时间为 "+testALRemoveT+"ns");

        MyLinkedList<Integer> testmyLinkedList=new MyLinkedList<>();
        long testLLAddST=System.nanoTime();
        for(int i=0;i<1000;i++){    //增1000项
            testmyLinkedList.add((int)(Math.random()*testmyLinkedList.size()),i);//(int)(Math.random()*100)
        }
        long testLLAddET=System.nanoTime();
        long testLLAddT=testLLAddET-testLLAddST;
        System.out.println("测试1000次myLinkedList的add操作的运行时间为 "+testLLAddT+"ns");

        long testLLGetST=System.nanoTime();
        for(int i=0;i<1000;i++){   //查1000项
            testmyLinkedList.get(i);
        }
        long testLLGetET=System.nanoTime();
        long testLLGetT=testLLGetET-testLLGetST;
        System.out.println("测试1000次myLinkedList的get操作的运行时间为 "+testLLGetT+"ns");

        long testLLSetST=System.nanoTime();
        for(int i=0;i<1000;i++){   //改1000项
            testmyLinkedList.set(i,0);
        }
        long testLLSetET=System.nanoTime();
        long testLLSetT=testLLSetET-testLLSetST;
        System.out.println("测试1000次myLinkedList的set操作的运行时间为 "+testLLSetT+"ns");

        long testLLRemoveST=System.nanoTime();
        for(int i=0;i<1000;i++){    //删1000项
            testmyLinkedList.remove((int)(Math.random()*testmyLinkedList.size()));
        }
        long testLLRemoveET=System.nanoTime();
        long testLLRemoveT=testLLRemoveET-testLLRemoveST;
        System.out.println("测试1000次myLinkedList的remove操作的运行时间为 "+testLLRemoveT+"ns");
    }
}
