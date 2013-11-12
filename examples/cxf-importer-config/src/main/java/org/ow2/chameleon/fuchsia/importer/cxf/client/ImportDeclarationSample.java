package org.ow2.chameleon.fuchsia.importer.cxf.client;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Validate;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.ow2.chameleon.fuchsia.core.declaration.ImportDeclaration;
import org.ow2.chameleon.fuchsia.core.declaration.ImportDeclarationBuilder;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Component
@Instantiate
public class ImportDeclarationSample {

    BundleContext context;

    public ImportDeclarationSample(BundleContext context){

        this.context=context;

    }

    @Validate
    public void start(){
        importDeclaration();

    }

    private void importDeclaration(){
        Map<String, Object>  metadata=new HashMap<String, Object>();

        metadata.put("id","b");
        metadata.put("className","org.ow2.chameleon.fuchsia.exporter.cxf.examples.base.PojoSampleToBeExportedIface");
        metadata.put("jax-ws.importer.interfaces","[org.ow2.chameleon.fuchsia.exporter.cxf.examples.base.PojoSampleToBeExportedIface]");
        metadata.put("endpoint.url","http://localhost:8080/cxf/PojoSampleToBeExported");

        ImportDeclaration declaration = ImportDeclarationBuilder.fromMetadata(metadata).build();

        Dictionary<String, Object> props = new Hashtable<String, Object>();
        props.put("endpoint.url","http://localhost:8080/cxf/PojoSampleToBeExported");
        String clazzes[] = new String[]{ImportDeclaration.class.getName()};
        ServiceRegistration registration = context.registerService(clazzes, declaration, props);
    }


}
