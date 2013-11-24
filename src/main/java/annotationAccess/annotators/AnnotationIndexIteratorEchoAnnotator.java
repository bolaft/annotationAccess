/**
 * 
 */
package annotationAccess.annotators;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import annotationAccess.util.MiscUtil;

/**
 * Annotator which iterates over the whole annotation index and 
 * echoes both the type and the covered text of each annotation 
 * using low level methods
 */
public class AnnotationIndexIteratorEchoAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		FSIterator<Annotation> i = aJCas.getAnnotationIndex().iterator();
		
		while (i.isValid()) {
			MiscUtil.echo(i.get());
			i.moveToNext();
		}
	}
}