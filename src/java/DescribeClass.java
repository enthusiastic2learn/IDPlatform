/*****************************************************************************
 * Source code information
 * -----------------------
 * Original author    Ian Dickinson, HP Labs Bristol
 * Author email       ian.dickinson@hp.com
 * Package            Jena 2
 * Web                http://sourceforge.net/projects/jena/
 * Created            25-Jul-2003
 * Filename           $RCSfile: DescribeClass.java,v $
 * Revision           $Revision: 1.1 $
 * Release status     $State: Exp $
 *
 * Last modified on   $Date: 2005/10/06 17:49:06 $
 *               by   $Author: andy_seaborne $
 *
 * (c) Copyright 2002, 2003, 2004, 2005 Hewlett-Packard Development Company, LP
 * (see footer for full conditions)
 *****************************************************************************/

// Package
///////////////

// Imports
///////////////
import java.io.PrintStream;
import java.util.*;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.util.iterator.*;



/**
 * <p>
 * Simple example of describing the basic attributes of a OWL, DAML or RDFS class
 * using the ontology API.  This is not meant as a definitive solution to the problem,
 * but as an illustration of one approach to solving the problem. This example should
 * be adapted as necessary to provide a given application with the means to render
 * a class description in a readable form.
 * </p>
 *
 * @author Ian Dickinson, HP Labs
 *         (<a  href="mailto:Ian.Dickinson@hp.com" >email</a>)
 * @version CVS $Id: DescribeClass.java,v 1.1 2005/10/06 17:49:06 andy_seaborne Exp $
 */
public class DescribeClass {
    // Constants
    //////////////////////////////////



    // Static variables
    //////////////////////////////////

    // Instance variables
    //////////////////////////////////

    private Map m_anonIDs = new HashMap();
    private int m_anonCount = 0;


    // Constructors
    //////////////////////////////////

    // External signature methods
    //////////////////////////////////

    /**
     * <p>Describe the given ontology class in texttual form. The description
     * produced has the following form (approximately):
     * <pre>
     * Class foo:Bar
     *    is a sub-class of foo:A, ex:B
     *    is a super-class of ex:C
     * </pre>
     * </p>
     *
     * @param out The print stream to write the description to
     * @param cls The ontology class to describe
     */
    public void describeClass( PrintStream out, OntClass cls ) {
        //renderClassDescription( out, cls );
        out.print("Classe ");
        out.println(cls.getModel().getGraph().getPrefixMapping().shortForm(cls.getURI()));

        // sub-classes
        for (Iterator i = cls.listSuperClasses( true ); i.hasNext(); ) {
            //out.print( "  is a sub-class of " );
            //renderClassDescription( out, (OntClass) i.next() );
            OntClass c = (OntClass) i.next();
            if (c.isRestriction()) {
                //renderRestriction( out, c.asRestriction() );
                Resource rc = (Resource) c.asRestriction().getOnProperty();
                //renderURI( out, rc.getModel(), rc.getURI() );
                out.println( rc.getModel().shortForm( rc.getURI() ) );
            }
            //out.println();
        }

        // super-classes
        for (Iterator i = cls.listSubClasses( true ); i.hasNext(); ) {
            //out.print( "  is a super-class of " );
            //renderClassDescription( out, (OntClass) i.next() );
            OntClass c = (OntClass) i.next();
            if (c.isRestriction()) {
                //renderRestriction( out, c.asRestriction() );
                Resource rc = (Resource) c.asRestriction().getOnProperty();
                //renderURI( out, rc.getModel(), rc.getURI() );
                out.println( rc.getModel().shortForm( rc.getURI() ) );
            }
            //out.println();
        }
     
    }

    /**
     * <p>Render a description of the given class to the given output stream.</p>
     * @param out A print stream to write to
     * @param c The class to render
     */
    public void renderClassDescription( PrintStream out, OntClass c ) {
        //if (c.isUnionClass()) {
        //    renderBooleanClass( out, "union", c.asUnionClass() );
        //}
        //else if (c.isIntersectionClass()) {
        //    renderBooleanClass( out, "intersection", c.asIntersectionClass() );
        //}
        //else if (c.isComplementClass()) {
        //    renderBooleanClass( out, "complement", c.asComplementClass() );
        //}
        if (c.isRestriction()) {
            //renderRestriction( out, c.asRestriction() );
            Resource rc = (Resource) c.asRestriction().getOnProperty();
            //renderURI( out, rc.getModel(), rc.getURI() );
            out.println( rc.getModel().shortForm( rc.getURI() ) );
        }
        //else {
        //    if (!c.isAnon()) {
                //out.print( "Classe: " );
                //out.print( ' ' );
                //out.println(c.getModel().getGraph().getPrefixMapping().shortForm(c.getURI()));
                //out.print( ' ' );
        //    }
        //    else {
        //        renderAnonymous( out, c, "class" );
        //    }
        //}
    }


    // Internal implementation methods
    //////////////////////////////////

    /**
     * <p>Handle the case of rendering a restriction.</p>
     * @param out The print stream to write to
     * @param r The restriction to render
     */
    protected void renderRestriction( PrintStream out, Restriction r ) {
        if (!r.isAnon()) {
            //out.print( "Restriction " );
            //renderURI( out, prefixesFor( r ), r.getURI() );
        }
        else {
            //renderAnonymous( out, r, "restriction" );
        }

        //out.println();

        renderRestrictionElem( out, "    on property", r.getOnProperty() );
        
        out.println();
/*
        if (r.isAllValuesFromRestriction()) {
            renderRestrictionElem( out, "    all values from", r.asAllValuesFromRestriction().getAllValuesFrom() );
        }
        if (r.isSomeValuesFromRestriction()) {
            renderRestrictionElem( out, "    some values from", r.asSomeValuesFromRestriction().getSomeValuesFrom() );
        }
        if (r.isHasValueRestriction()) {
            renderRestrictionElem( out, "    has value", r.asHasValueRestriction().getHasValue() );
        }
 */ 
    }

    protected void renderRestrictionElem( PrintStream out, String desc, RDFNode value ) {
        //out.print( desc );
        out.print( " " );
        renderValue( out, value );
    }

    protected void renderValue( PrintStream out, RDFNode value ) {
        //if (value.canAs( OntClass.class )) {
        //    renderClassDescription( out, (OntClass) value.as( OntClass.class ) );
        //}
        //else if (value instanceof Resource) {
            Resource r = (Resource) value;
        //    if (r.isAnon()) {
        //        renderAnonymous( out, r, "resource" );
            //}
            //else {
                renderURI( out, r.getModel(), r.getURI() );
            //}
        //}
        //else if (value instanceof Literal) {
        //    out.print( ((Literal) value).getLexicalForm() );
        //}
        //else {
        //   out.print( value );
        //}
    }

    protected void renderURI( PrintStream out, PrefixMapping prefixes, String uri ) {
        out.print( prefixes.shortForm( uri ) );
    }

    protected PrefixMapping prefixesFor( Resource n ) {
        return n.getModel().getGraph().getPrefixMapping();
    }

    protected void renderAnonymous( PrintStream out, Resource anon, String name ) {
        String anonID = (String) m_anonIDs.get( anon.getId() );
        if (anonID == null) {
            anonID = "a-" + m_anonCount++;
            m_anonIDs.put( anon.getId(), anonID );
        }

        out.print( "Anonymous ");
        out.print( name );
        out.print( " with ID " );
        out.print( anonID );
    }

    protected void renderBooleanClass( PrintStream out, String op, BooleanClassDescription boolClass ) {
        out.print( op );
        out.println( " of {" );

        for (Iterator i = boolClass.listOperands(); i.hasNext(); ) {
            out.print( "      " );
            renderClassDescription( out, (OntClass) i.next() );
            out.println();
        }
        out.print( "  } " );
    }

    // ************************************************************************
        
    
    // ************************************************************************

    public static void main( String[] args ) {
        // read the argument file, or the default
        String source = "http://localhost:8084/ModuloAutoria/owl/ontology9.owl";

        OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        // read the source document
        m.read(source);
       
        //OntClass classe = m.getOntClass("http://www.owl-ontologies.com/emathema.owl#ExternalView");
        
        //DescribeClass dc = new DescribeClass();
        
        Iterator i = m.listClasses().filterDrop(
                new Filter() {

                    public boolean accept(Object o) {
                        return ((Resource) o).isAnon();
                    }
                });

        while (i.hasNext()) {

            OntClass classe = (OntClass)i.next();
            System.out.print("Classe ");
            System.out.println(classe.getModel().getGraph().getPrefixMapping().shortForm(classe.getURI()));

            List propriedades = new LinkedList();
            
            for (Iterator i2 = classe.listSuperClasses(true); i2.hasNext();) {
                OntClass c = (OntClass) i2.next();
                if (c.isRestriction()) {
                    Resource rc = (Resource) c.asRestriction().getOnProperty();
                    //System.out.println(rc.getModel().shortForm(rc.getURI()));
                    if (!propriedades.contains(rc.getURI())) 
                        propriedades.add(rc.getURI());
                }
            }

            for (Iterator i2 = classe.listSubClasses(true); i2.hasNext();) {
                OntClass c = (OntClass) i2.next();
                if (c.isRestriction()) {
                    Resource rc = (Resource) c.asRestriction().getOnProperty();
                    //System.out.println(rc.getModel().shortForm(rc.getURI()));
                    if (!propriedades.contains(rc.getURI())) 
                        propriedades.add(rc.getURI());
                }
            }
            
            Collections.sort(propriedades);
            
            for(int x=0; x<propriedades.size(); x++) {
                System.out.println("   " + propriedades.get(x));
            }
       }
        
    }

    //==============================================================================
    // Inner class definitions
    //==============================================================================

}


/*
    (c) Copyright 2002, 2003, 2004, 2005 Hewlett-Packard Development Company, LP
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions
    are met:

    1. Redistributions of source code must retain the above copyright
       notice, this list of conditions and the following disclaimer.

    2. Redistributions in binary form must reproduce the above copyright
       notice, this list of conditions and the following disclaimer in the
       documentation and/or other materials provided with the distribution.

    3. The name of the author may not be used to endorse or promote products
       derived from this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
    IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
    IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
    NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
    DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
    THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
    (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
    THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/


