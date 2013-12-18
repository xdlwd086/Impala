// Copyright 2012 Cloudera Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.cloudera.impala.common;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Integer ids that cannot accidentally be compared with ints.
 *
 */
public class Id<IdType extends Id<IdType>> {
  protected final int id_;

  static private int INVALID_ID = -1;

  public Id() {
    this.id_ = INVALID_ID;
  }

  public Id(int id) {
    this.id_ = id;
  }

  public boolean isValid() { return id_ != INVALID_ID; }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    // only ids of the same subclass are comparable
    if (obj.getClass() != this.getClass()) return false;
    return ((Id)obj).id_ == id_;
  }

  @Override
  public int hashCode() {
    return Integer.valueOf(id_).hashCode();
  }

  public int asInt() {
    return id_;
  }

  public ArrayList<IdType> asList() {
    ArrayList<IdType> list = new ArrayList<IdType>();
    list.add((IdType) this);
    return list;
  }

  @Override
  public String toString() {
    return Integer.toString(id_);
  }

  public static <C extends Id> String printIds(List<C> ids) {
    ArrayList<String> l = Lists.newArrayList();
    for (C id: ids) {
      l.add(id.toString());
    }
    return "(" + Joiner.on(" ").join(l) + ")";
  }
}
