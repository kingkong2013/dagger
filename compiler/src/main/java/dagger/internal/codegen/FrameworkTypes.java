/*
 * Copyright (C) 2015 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dagger.internal.codegen;

import com.google.auto.common.MoreTypes;
import com.google.common.collect.ImmutableSet;
import dagger.producers.Produced;
import dagger.producers.Producer;
import javax.lang.model.type.TypeMirror;

/**
 * A collection of utility methods for dealing with Dagger framework types. A framework type is any
 * type that the framework itself defines.
 */
final class FrameworkTypes {
  // NOTE(beder): ListenableFuture is not considered a producer framework type because it is not
  // defined by the framework, so we can't treat it specially in ordinary Dagger.
  private static final ImmutableSet<Class<?>> PRODUCER_TYPES =
      ImmutableSet.of(Produced.class, Producer.class);

  /** Returns true if the type represents a producer-related framework type. */
  static boolean isProducerType(TypeMirror type) {
    if (!MoreTypes.isType(type)) {
      return false;
    }
    for (Class<?> clazz : PRODUCER_TYPES) {
      if (MoreTypes.isTypeOf(clazz, type)) {
        return true;
      }
    }
    return false;
  }

  private FrameworkTypes() {}
}
