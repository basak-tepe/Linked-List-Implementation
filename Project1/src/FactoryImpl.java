import java.util.NoSuchElementException;

public class FactoryImpl implements Factory{
	
	//DATA FIELDS / ATTRIBUTES

	private Holder first; 
	private Holder last; 
	private Integer size;
	
	
	//CONSTRUCTOR
	
	FactoryImpl(){
		
		
		 //our first factory line consists of 1 holder: first & last at the same time.
		 //that makes the size 1.
		 	
		Holder onlyBox = new Holder(null,null,null);
		this.first = onlyBox;
		this.last = onlyBox;
		this.size = 1;
	}
	
	//METHODS 
	
	@Override
	public void addFirst(Product product) {
		
		
		//if the list is empty, we add the product to first position.
		if(first.getProduct() == null) {
			first.setProduct(product);
		}
				
		//if first position is full, we need to squeeze the list to the right.
		else {
			Holder newHolder = new Holder(null, product, null); //putting the element in a new holder.
			
			//connecting holders
			first.setPreviousHolder(newHolder);
			newHolder.setNextHolder(first);
			
			this.first = newHolder; //the element becomes new head.
			size++; //size is incremented
		}
	}

	@Override
	public void addLast(Product product) {
	
		//Analogue of addFirst
		
		//if the list is empty
		if(last.getProduct() == null){
			last.setProduct(product);
		}
		else {
			Holder newHolder = new Holder(null,product,null);
			last.setNextHolder(newHolder);
			newHolder.setPreviousHolder(last);
			this.last = newHolder;
			size++;
		}
	}

	@Override
	public Product removeFirst() throws NoSuchElementException {
		
		//if the list is empty there is no such element
		if(first.getProduct() == null) {
			throw new NoSuchElementException();
		}
	
		//otherwise method continues
		Product product = first.getProduct();
		first.setProduct(null);
		
		//if size is greater than one, we need to make the second holder the new first holder.
		if(size>1){
			Holder secondHolder = first.getNextHolder();
			secondHolder.setPreviousHolder(null);
			first = secondHolder;
			size--;
		}
		
		return product;
	}

	@Override
	public Product removeLast() throws NoSuchElementException {
		
		//Analogue of removeFirst 
		
		if(last.getProduct() == null) {
			throw new NoSuchElementException();
		}

		Product product = last.getProduct();
		last.setProduct(null);
		
		if(size>1){
			
			Holder penultimateBox = last.getPreviousHolder();	
			penultimateBox.setNextHolder(null);
			last = penultimateBox;
			size--;
		}
	
		return product;
		
		
	}

	@Override
	public Product find(int id) throws NoSuchElementException {
		
		
		//We use currentFirst to traverse through the list.
		//We will start from the first item.
		Holder currentHolder = first;
		boolean found = false;
		
		

		//if the list is empty, then there is no product
		if(first.getProduct()==null) {
			throw new NoSuchElementException();
		}
		
		//We use currentProduct to traverse through products.
		Product currentProduct = currentHolder.getProduct();
		
		for(int i=0;i<size; i++) {
			currentProduct = currentHolder.getProduct();
			int identity = currentProduct.getId();
		
			//break statement
			if(Integer.compare(id, identity)==0) {
				found = true;
				break;
			}
			
			currentHolder = currentHolder.getNextHolder();
			
		}
		
		if(!found) {
			throw new NoSuchElementException();
		}
		 
		 return currentProduct;
	}

	@Override
	public Product update(int id, Integer value) throws NoSuchElementException {
		
		//We use currentFirst to traverse through the list.
		//We will start from the first item.
		Holder currentHolder = first;
		boolean found = false;
		
		//if the list is empty, then there is no such product.
		if(first.getProduct() ==null) {
			throw new NoSuchElementException();
		}
		
		//We use currentProduct to traverse through products.
		Product currentProduct = currentHolder.getProduct();
		
		for(int i=0;i<size; i++) {
			currentProduct = currentHolder.getProduct();
			int identity = currentProduct.getId();
		
			//break statement
			if(Integer.compare(id, identity)==0) {
				found = true;
				break;
			}
			
			currentHolder = currentHolder.getNextHolder();
			
		}
		
		if(!found) {
			throw new NoSuchElementException();
		}
		//we should return the previous version of the product.
		 Product prevProduct = new Product(currentProduct.getId(),currentProduct.getValue());
		 currentProduct.setValue(value);
		 return prevProduct;
	}

	@Override
	public Product get(int index) throws IndexOutOfBoundsException {

		//checking if the list is empty or index is out of bounds.
		if(index>=size || first.getProduct() ==null) {
			throw new IndexOutOfBoundsException();
		}
		
		//We use currentFirst to traverse through the list.
		//We will start from the first item.
		Holder currentHolder = first;
		boolean found = false;
		
		for(int i =0; i<size; i++) {
			if(Integer.compare(index,i)==0) {
				found = true;
				break;
			}
			currentHolder = currentHolder.getNextHolder();	
		}
		if(!found) {
			throw new IndexOutOfBoundsException();
		}
		
		return currentHolder.getProduct();
	}

	@Override
	public void add(int index, Product product) throws IndexOutOfBoundsException {
		
		//if index is out of bounds without being 0.
		if(index>=size && index!=0) {
			throw new IndexOutOfBoundsException();
		}
	
		//if the list is empty
		if(first.getProduct() ==null) {
			addFirst(product);		
		}
		
		//if the insertion is done at the top
		else if(Integer.compare(index, 0) == 0) {
			addFirst(product);	
		}
		
		//add method cannot add to last position.
		//no need to cover this case.
		
		else {
			//We use currentFirst to traverse through the list.
			//We will start from the first item.
			Holder currentHolder = first;		
			for(int i= 0;i<size;i++) {
				
				if(Integer.compare(index, i)== 0) {
					this.size++;
					break;
				}
				currentHolder = currentHolder.getNextHolder();						
			}
		
				
			//if there is more than 1 item.
			//in a sense, we have to make room for the new product.
			//by shifting the partition to the right.
			
			if(size>1) {
				Holder leftHolder = currentHolder.getPreviousHolder();
				Holder newHolder = new Holder(leftHolder,product,currentHolder);
				leftHolder.setNextHolder(newHolder);
				currentHolder.setPreviousHolder(newHolder);
			}			
		}		
	}

	@Override
	public Product removeIndex(int index) throws IndexOutOfBoundsException {

		//We use currentFirst to traverse through the list.
		//We will start from the first item.
		Holder currentHolder = first;	
		
	
		if(index>=size ||first.getProduct()== null){
			//not exactly true
			//but can I throw 2 different errors* 
			//index out of bounds & no such element
			throw new IndexOutOfBoundsException();
		}
		
		
		
		for(int i = 0;i<index;i++) {
			currentHolder = currentHolder.getNextHolder();
		}
		
		Product removed = currentHolder.getProduct();
			
		//First position case
		if(currentHolder == first) {
			removeFirst();
		}
		//Last position case
		else if(currentHolder == last) {
			removeLast();
		}
		
		//for 3 and more items.
		else if(size>2) {
		Holder prev = currentHolder.getPreviousHolder();
		Holder next = currentHolder.getNextHolder();
		prev.setNextHolder(next);
		next.setPreviousHolder(prev);
		size--;
		
		}
		return removed;
		
	}
	

	

	@Override
	public Product removeProduct(int value) throws NoSuchElementException {
		
		//if the list is empty
		if(first.getProduct()==null) {
			throw new NoSuchElementException();
		}
		
		//We use currentFirst to traverse through the list.
		//We will start from the first item.
		Holder currentHolder = first;	
		
		
		//We use currentProduct to traverse through products.
		Product  currentProduct = currentHolder.getProduct();
		//and currentValue to traverse through values.
		Integer currentValue;
		boolean found = false;
	
		for (int i = 0;i<size;i++) { //until the end
			
			currentProduct = currentHolder.getProduct();
			currentValue = currentProduct.getValue();
			
			//break statement	
			if (Integer.compare(value, currentValue)==0) {
				found = true;
				break;
			}
			currentHolder = currentHolder.getNextHolder();
			
		}
		
		if(!found) {
			throw new NoSuchElementException();
		}
		
		//first position case
		if(currentHolder == first) {
			removeFirst();
		}
		//last position case
		else if(currentHolder == last) {
			removeLast();
		}
		
		//for 3 and more items.
		else if(size>2) {
		Holder prev = currentHolder.getPreviousHolder();
		Holder next = currentHolder.getNextHolder();
		prev.setNextHolder(next);
		next.setPreviousHolder(prev);
		size--;
		
		}
		return currentProduct;
	}

	@Override
	public int filterDuplicates() {
		
		//we will traverse all list 
		//outer iteration will hold a constant product. i.e #1 item to be compared.
		//inner iteration will traverse the remaining elements and compare it to the one that is held constant. i.e #2 item to be compared.
		
		int removed = 0;
		int referenceVal = 0; //#1
		int comparisonVal = 0; //#2
		Holder referenceHolder = first; //#1
		Holder comparisonHolder = first; //#2
		
		if(size>1) {
		for (int i = 0; i<size; i++) {
			referenceVal = referenceHolder.getProduct().getValue();
			comparisonHolder = referenceHolder.getNextHolder();
			for(int j=i+1;j<size;j++) {
				comparisonVal = comparisonHolder.getProduct().getValue();
				
				if(Integer.compare(referenceVal, comparisonVal)== 0) {
					removeIndex(j);
					removed++;
				}
				comparisonHolder = comparisonHolder.getNextHolder();
			}
			referenceHolder = referenceHolder.getNextHolder();
		}
			
		}
		return removed;
	}

	
	@Override
	public void reverse() {
	
		//reversing means swapping the previous and next informations on a holder.
		
		if(size>1) { //size needs to be larger than 1 to reverse.
			
		//swapping for first element.
		
			Holder first = this.first;
			Holder nextInfo = first.getNextHolder();
			first.setPreviousHolder(nextInfo);
			first.setNextHolder(null);
			
		//swapping for the last element
			Holder last = this.last;
			Holder prevInfo = last.getPreviousHolder();
			last.setNextHolder(prevInfo);
			last.setPreviousHolder(null);
			
			this.first = last;
			this.last = first;
		}
		
		//if there is more than 2 elements(i.e elements in bw first and last holder)
		//we need to keep swapping.
		
		
		if (size>=3) {
			Holder currentHolder = first.getNextHolder();  //2nd node. 
			for(int i = 1;i<size-1;i++) { //traversing from 2nd to penultimate holder.
				Holder prevInfo = currentHolder.getPreviousHolder();
				Holder nextInfo = currentHolder.getNextHolder();
				currentHolder.setNextHolder(prevInfo);
				currentHolder.setPreviousHolder(nextInfo);
				currentHolder = currentHolder.getNextHolder();
				
			}
		}

	}
	
	public String toString() {
		
		String factory = "";
		
		//if the factory line is empty
		if(first.getProduct() ==null) {
			factory = "{}";
		}

		else {
			Holder currentHolder = first;
			factory = "{";
			for(int i=0;i<size-1;i++) {
				Product currentProduct = currentHolder.getProduct();
				factory = factory + currentProduct+",";
				currentHolder = currentHolder.getNextHolder();
			}
			factory = factory + last.getProduct();
			factory = factory + "}";
			System.out.println();
		}
		
		
		return factory;
	}
	
	
}



















