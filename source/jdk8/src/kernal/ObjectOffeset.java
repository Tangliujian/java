package kernal;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 得到对象的地址
 * 
 * @author 晓风轻
 *
 */

class OffesetWrap {
  final Object obj;

  public OffesetWrap(Object o) {
    this.obj = o;
  }
}

public class ObjectOffeset {

  private int count = 12345;

  static Unsafe unsafe;
  static {
    try {
      Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
      declaredField.setAccessible(true);
      unsafe = (Unsafe) declaredField.get(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args)
      throws NoSuchFieldException, SecurityException {

    // java.lang.SecurityException: Unsafe
    // unsafe = Unsafe.getUnsafe();

    ObjectOffeset obj = new ObjectOffeset();

    OffesetWrap demo = new OffesetWrap(obj);

    long objFieldOffset = getFieldOffset(OffesetWrap.class, "obj");
    System.out.println("字段obj偏移量：" + objFieldOffset);

    // 引用就是long（64位，可以用unsafe.addressSize() == 8 判断）
    System.out.println("address: " + unsafe.addressSize());

    long objOffset = unsafe.getLong(demo, objFieldOffset);
    System.out.println("新的ObjectOffeset对象地址：" + objOffset);

    // 测试，读取对象的count字段
    long countFieldOffset = getFieldOffset(ObjectOffeset.class, "count");

    // 读取对象字段
    int int1 = unsafe.getInt(obj, countFieldOffset);
    System.out.println("第1种方法：" + int1);

    // FIXME 使用对象偏移量读取对象字段
    int int2 = unsafe.getInt(objOffset + countFieldOffset);
    System.out.println("第2种方法：" + int2);
  }

  /**
   * 得到对象的字段偏移量
   * 
   * @param cls
   * @param name
   * @return
   * @throws NoSuchFieldException
   */
  private static long getFieldOffset(Class<?> cls, String name)
      throws NoSuchFieldException {
    return unsafe.objectFieldOffset(cls.getDeclaredField(name));
  }

}
