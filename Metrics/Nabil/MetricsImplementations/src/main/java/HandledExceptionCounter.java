import org.objectweb.asm.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandledExceptionCounter {

    public static void main(String[] args) throws Exception {

        String filePath = "/home/nabilkara/MetricsImplementations/src/test/java/test.java";
        DynamicClassLoading.compileJavaFile(filePath);
        filePath = filePath.replace(".java", ".class");
        Path path = Paths.get(filePath);
        byte[] bytecode = Files.readAllBytes(path);
        ClassReader classReader = new ClassReader(bytecode);
        HandledExceptionVisitor visitor = new HandledExceptionVisitor();
        classReader.accept(visitor, ClassReader.EXPAND_FRAMES);
        System.out.println("Number of Handled Exceptions: " + visitor.getHandledExceptionCount());
    }

    static class HandledExceptionVisitor extends ClassVisitor {

        private int handledExceptionCount = 0;

        public HandledExceptionVisitor() {
            super(Opcodes.ASM9);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new HandledExceptionMethodVisitor(mv);
        }

        public int getHandledExceptionCount() {
            return handledExceptionCount;
        }

        class HandledExceptionMethodVisitor extends MethodVisitor {

            public HandledExceptionMethodVisitor(MethodVisitor methodVisitor) {
                super(Opcodes.ASM9, methodVisitor);
            }

            @Override
            public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
                super.visitTryCatchBlock(start, end, handler, type);
                if (type != null) {
                    // Increment count if the exception is handled
                    handledExceptionCount++;
                }
            }
        }
    }
}

