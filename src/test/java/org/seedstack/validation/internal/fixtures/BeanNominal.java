/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.validation.internal.fixtures;


public class BeanNominal {
	    private int age;

	    private Long longNumber;

	    private String name;

	    private String firstName;
	    
	    public BeanNominal()
	    {
	    }

	    public int getAge()
	    {
	        return age;
	    }

	    public void setAge(int age)
	    {
	        this.age = age;
	    }

	    public String getName()
	    {
	        return name;
	    }

	    public void setName(String name)
	    {
	        this.name = name;
	    }

	    public String getFirstName()
	    {
	        return firstName;
	    }

	    public void setFirstName(String firstName)
	    {
	        this.firstName = firstName;
	    }

	    public Long getLongNumber()
	    {
	        return longNumber;
	    }

	    public void setLongNumber(Long longNumber)
	    {
	        this.longNumber = longNumber;
	    }

}
