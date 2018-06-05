/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Ferenc Karsany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package hu.karsany.querybean.model;

/**
 * Created by fkarsany on 2015.01.03..
 */
public class PojoField {

    private final String fieldName;
    private final String fieldType;
    private boolean readonly;

    public PojoField(String fieldName, String fieldType, boolean readonly) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.readonly = readonly;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldNameInitCap() {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public String getFieldType() {
        return fieldType;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public boolean isSettable() {
        return !isReadonly();
    }

}
