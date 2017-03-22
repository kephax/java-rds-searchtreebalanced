public class SearchTreeBalanced<K extends Comparable<K>, V>
{
	/* /// ATTRIBUTES /// */
	private SearchTreeBalanced<K,V> myLeft;
	private SearchTreeBalanced<K,V> myRight;
	private K key;
	private V item;
	
	/* /// CONSTRUCTORS /// */
	public SearchTreeBalanced()
	{
		// leave empty to create sentinels with value null
	}
	
	public SearchTreeBalanced( SearchTreeBalanced<K, V> left, SearchTreeBalanced<K, V> right )
	{
		myLeft = left;
		myRight = right;
	}
	
	/* /// METHODS /// */

	/* /// GetSize ////////////////////////////////////// */
	public int getSize()
	{
		if ( isEmpty() )
		{
			return 0;
		}
		else
		{
			return myLeft.getSize() + myRight.getSize() + 1; // the +1 is because of the class where we r now
		}
	}
	
	/* /// IsEmpty ////////////////////////////////////// */
	public boolean isEmpty()
	{
		if( ( key  == null )&&
			( item == null ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/* /// GetHeight //////////////////////////////////// */	
	public int getHeight()
	{
		if( !isEmpty() )
		{
			return Math.max( myLeft.getHeight() + 1, myRight.getHeight() + 1 );
		}
		else
		{
			return 0;
		}
	}
		
	/* /// Put ///////////////////////////////////////// */
	public void put( K aKey, V aValue )
	{ 
		if ( isEmpty() )
		{
			item = aValue;
			key = aKey;
			
			myLeft = new SearchTreeBalanced<K, V>();
			myRight = new SearchTreeBalanced<K, V>();
		}
		else
		{
			if ( aKey.compareTo( key ) < 0 ) // als kleiner 0, dan kleiner
			{
				myLeft.put( aKey, aValue );
			}
			else if ( aKey.compareTo( key ) > 0 )
			{
				myRight.put( aKey, aValue );				
			}
		}
	} 
	
	/* /// ContainsKey ////////////////////////////////// */
	public boolean containsKey( K aKey )
	{
		if ( isEmpty() )
		{
			return false; // if tree empty
		}
		else
		{
			if ( aKey.compareTo( key ) == 0 )
			{
				return true; 
			}
			else if ( aKey.compareTo( key ) < 0 )
			{
				myLeft.containsKey( aKey );
			}
			else if ( aKey.compareTo( key ) > 0 )
			{
				myRight.containsKey( aKey );				
			}
		}
		return false; // if tree not empty, but not found
	}
	
	
	/* /// Contains ///////////////////////////////////// */
	public boolean contains( V aValue )
	{
		if ( isEmpty() )
		{
			return false; // if tree empty
		}
		else
		{
			if ( item != aValue )
			{
				myLeft.contains( aValue );
				myRight.contains( aValue );
			}
			else
			{
				return true; // himself containes the value
			}
		}
		return false; // if tree not empty, but not found	
	}
	
	
	/* /// get ////////////////////////////////////////// */
	public V get( K aKey )
	{
		if ( isEmpty() )
		{
			return null; // if tree is null
		}
		else
		{
			if( key.compareTo( aKey ) == 0 )
			{
				return item;
			}
			else if( key.compareTo( aKey ) < 0 )
			{
				myLeft.get( aKey );
			}
			else if( key.compareTo( aKey ) > 0 )
			{
				myRight.get( aKey );
			}
		}
		return null; // if not found
	}

	/* /// isLeaf /////////////////////////////////////// */
	private boolean isLeaf()
	{
		if( ( myLeft == null)&&( myRight == null ) )
		{
			return true;
		}
		else // wtf is this?
		{
			myLeft.isLeaf();
			myRight.isLeaf();
		}
		return false;
	}
		

	
	/* /// remove /////////////////////////////////////// */
	public void remove( K aKey )
	{
	
		if( !isEmpty() )
		{
		if ( key == aKey )
		{
			if ( isLeaf() )
			{
				removeLeaf();
			}
			else
			{
				SearchTreeBalanced<K, V> tmp = null;
				
				if( myLeft.isEmpty() )
				{
					tmp = myRight.getSmallest();
					myRight.remove( tmp.getKey() );
				}
				else
				{
					tmp = myLeft.getLargest();
					myLeft.remove( tmp.getKey() );
				}

				
				key = tmp.getKey();					
				item = tmp.getValue() ;

			}	
		}
		else if ( aKey == myLeft.get( aKey ) ) //anders als in linkersub
		{
			myLeft.remove( aKey );
		}
		else 
		{
			myRight.remove( aKey );
		}
		}
	 }

	/* /// removeLeaf /////////////////////////////////// */
	private void removeLeaf()
	{
		key = null;
		item = null;
		myLeft = null;
		myRight = null;
	}

	
	/*/// largest /////////////////////////////////////// */
	public K largest()
	{
		//return ( isEmpty() || myRight.isEmpty() ) ? this : myRight.getLargest();
		if ( isEmpty() || myRight.isEmpty() )
		{
			return null;
		}
		else
		{
			return myRight.getLargest().getKey();
		}
	}
	
	/* /// getLargest /////////////////////////////////// */
	public SearchTreeBalanced<K, V> getLargest()
	{
		if( ( isEmpty() )||( myRight.isEmpty() ) )
		{
			return this;
		}
		else
		{
			return myRight.getLargest();
		}
	}
	
	/*/// getKleinste /////////////////////////////////// */
	public SearchTreeBalanced<K, V> getSmallest()
	{
		System.out.println("test");
		if( ( isEmpty() ) || ( myLeft.isEmpty() ) )
		{
			return this;
		}
		else
		{
			return myLeft.getSmallest();
		}
	}	
		
	/* /// getKey /////////////////////////////////////// */
	public K getKey()
	{
		return key;
	}
	
	/* /// getvalue ///////////////////////////////////// */
	public V getValue()
	{
		return item;
	}
	
	/*///  /// */
	public void balance()
	{
	
	}
	
	/* /// toString ///////////////////////////////////// */
	public String toString()
	{
		//"{" + item + "}"
		if ( !this.isEmpty() )
		{
/*			return "+ [" + key + "->"+ item + "] \n" + 
				   "  " + myLeft.toString() + "\n" +
				   "  " + myRight.toString() + "\n";*/
			return "(" + myLeft.toString() + "," + key + "->"+ item + "," + myRight.toString()+ ")";
		}
		return "_";
	}
}

