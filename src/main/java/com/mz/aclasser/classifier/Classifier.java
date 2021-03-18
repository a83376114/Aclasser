package com.mz.aclasser.classifier;

public interface Classifier<I, O> {
    O classify(I output);
    void release();
}
