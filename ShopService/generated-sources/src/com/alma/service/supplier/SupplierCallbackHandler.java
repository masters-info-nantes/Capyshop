
/**
 * SupplierCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v14  Built on : Jul 25, 2015 (11:19:54 IST)
 */

    package com.alma.service.supplier;

    /**
     *  SupplierCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SupplierCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SupplierCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SupplierCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for validate method
            * override this method for handling normal response from validate operation
            */
           public void receiveResultvalidate(
                    com.alma.service.supplier.SupplierStub.ValidateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from validate operation
           */
            public void receiveErrorvalidate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getProducts method
            * override this method for handling normal response from getProducts operation
            */
           public void receiveResultgetProducts(
                    com.alma.service.supplier.SupplierStub.GetProductsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getProducts operation
           */
            public void receiveErrorgetProducts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for saveOrder method
            * override this method for handling normal response from saveOrder operation
            */
           public void receiveResultsaveOrder(
                    com.alma.service.supplier.SupplierStub.SaveOrderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveOrder operation
           */
            public void receiveErrorsaveOrder(java.lang.Exception e) {
            }
                


    }
    