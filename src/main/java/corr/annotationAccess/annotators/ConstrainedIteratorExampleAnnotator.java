/**
 * 
 */
package corr.annotationAccess.annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.ConstraintFactory;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import annotationAccess.util.MiscUtil;

import common.types.POS;

/**
 * Annotator which iterates over an iterator 
 * built by applying a constraint on a more global iterator
 */
public class ConstrainedIteratorExampleAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {			
		// Build the desired constraint
		FSMatchConstraint nounOrVerbConstraint = buildANounOrVerbTypeConstraint(aJCas);
		
		// Create a filtered iterator from some annotation iterator
		AnnotationIndex<Annotation>	aPOSAnnotationIndex = aJCas.getAnnotationIndex(POS.type);
		FSIterator<Annotation> aPOSAnnotationIterator = aPOSAnnotationIndex.iterator();
		FSIterator<Annotation> nounOrVerbAnnotationIterator = aJCas.createFilteredIterator(aPOSAnnotationIterator, nounOrVerbConstraint);

		// Consume
		while(nounOrVerbAnnotationIterator.isValid()) {
			Annotation nounOrVerbAnnotation =  (Annotation) nounOrVerbAnnotationIterator.get();

			MiscUtil.echo(nounOrVerbAnnotation);
			nounOrVerbAnnotationIterator.moveToNext();
		}
	}

	/**
	 * build a constraint to filter the feature structure type and 
	 * select only noun or verb annotations
	 * @param aJCas
	 * @return
	 */
	private FSMatchConstraint buildANounOrVerbTypeConstraint(JCas aJCas) {
		// -- create the constraint
		// Start by getting the constraint factory from the CAS.
		ConstraintFactory cf = aJCas.getConstraintFactory();

		// Create a new type constraint.  
		// Type constraints will check that structures
		// they match against have a type at least as specific
		// as the type specified in the constraint.
		FSTypeConstraint nounConstraint = cf.createTypeConstraint();
		// Set the type (by default it is TOP).
		// This succeeds if the type being tested by this constraint
		// is NN or a subtype of NN.
		nounConstraint.add(aJCas.getTypeSystem().getType("common.types.pos.NN"));

		//
		FSTypeConstraint verbConstraint = cf.createTypeConstraint();
		// Set the type (by default it is TOP).
		// This succeeds if the type being tested by this constraint
		// is V or a subtype of V.
		verbConstraint.add(aJCas.getTypeSystem().getType("common.types.pos.V"));

		//
		FSTypeConstraint properNameConstraint = cf.createTypeConstraint();
		properNameConstraint.add(aJCas.getTypeSystem().getType("common.types.pos.NP"));
		
		// Create the final constraint by conjoining the two constraints.
		FSMatchConstraint nounOrVerbConstraint = cf.or(verbConstraint, nounConstraint);
		FSMatchConstraint propernameOrNounOrVerbConstraint = cf.or(nounOrVerbConstraint, properNameConstraint);
		
		return propernameOrNounOrVerbConstraint;
	}
	
}
