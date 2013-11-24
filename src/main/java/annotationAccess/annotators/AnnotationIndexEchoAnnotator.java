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
 */
public class AnnotationIndexEchoAnnotator extends JCasAnnotator_ImplBase {
	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		AnnotationIndex<Annotation>	index = aJCas.getAnnotationIndex();

		for(Annotation annotation : index){
			MiscUtil.echo(annotation);
		}
	}
}
