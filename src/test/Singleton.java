package test;
public class Singleton {  
  
    /* ˽�й��췽������ֹ��ʵ���� */  
    private Singleton() {
    	System.out.println("======");
    }  
  
    /* �˴�ʹ��һ���ڲ�����ά������ */  
    private static class SingletonFactory {  
        private static Singleton instance = new Singleton();  
    }  
  
    /* ��ȡʵ�� */  
    public static Singleton getInstance() {  
        return SingletonFactory.instance;  
    }  
  
    /* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */  
    public Object readResolve() {  
        return getInstance();  
    }
}  