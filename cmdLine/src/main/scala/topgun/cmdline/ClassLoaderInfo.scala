package topgun.cmdline


import java.io.IOException

import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode

import scala.collection.mutable

class ClassLoaderInfo() {
  var allClasses = new mutable.HashMap[String, ClassInfo]()

  def lookup(className: String, classLoader: ClassLoader): ClassInfo = {
    if(className.contains("$$")){
      val splitIndex = className.indexOf("$$")
      val lambdaClassName = className.substring(0,splitIndex)
      allClasses.getOrElseUpdate(lambdaClassName, buildClassInfo(lambdaClassName, classLoader)): ClassInfo
    }else
      allClasses.getOrElseUpdate(className, buildClassInfo(className, classLoader)): ClassInfo
  }

  def buildClassInfo(className: String, classLoader: ClassLoader): ClassInfo = {
    val resourceName = className.replace(".", "/") + ".class"
    val iStream = classLoader.getResourceAsStream(resourceName)
    if(iStream ne null) {
      val reader = new ClassReader(iStream)
      val classNode = new ClassNode()
      // specify no parsing options.
      reader.accept(classNode, 0)
      iStream.close()
      val classNameFromReader = reader.getClassName
      val sourceFile = classNode.sourceFile
      val superClass = reader.getSuperName
      val interfaces: List[String] = reader.getInterfaces.toList
      ClassInfo(classNameFromReader, superClass, sourceFile, interfaces)
    }else
      ClassInfo(className,"super not found","sourceFile not found",List())
  }
}
