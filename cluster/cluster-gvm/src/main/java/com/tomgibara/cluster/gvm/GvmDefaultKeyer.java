/*
 * Copyright 2007 Tom Gibara
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.tomgibara.cluster.gvm;

/**
 * Merges keys by choosing the non-null key of the more massive cluster when
 * available. Where a key is being added to a cluster, any pre-existing key is
 * preserved.
 * 
 * @author Tom Gibara
 * 
 * @param <K>
 *            the key type
 */

public class GvmDefaultKeyer<S extends GvmSpace, K> implements GvmKeyer<S,K> {

	@Override
	public K mergeKeys(GvmCluster<S,K> c1, GvmCluster<S,K> c2) {
		K key = c1.getKey();
		return key == null ? c2.getKey() : key;
	}
	
	@Override
	public K addKey(GvmCluster<S,K> cluster, K key) {
		K k = cluster.getKey();
		return k == null ? key : k;
	}
	
	
	
}
