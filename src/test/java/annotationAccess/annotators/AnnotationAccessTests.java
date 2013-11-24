package annotationAccess.annotators;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngine;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.TypePrioritiesFactory.createTypePriorities;
import static org.apache.uima.fit.factory.TypeSystemDescriptionFactory.createTypeSystemDescription;
import static org.apache.uima.util.CasCreationUtils.createCas;

import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.metadata.TypePriorities;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;



import common.types.Constituent;
import common.types.NamedEntity;
import common.types.POS;
import common.types.Sentence;
import common.types.Token;
import common.types.pos.ART;
import common.types.pos.NN;
import common.types.pos.NP;
import common.types.pos.PP;
import common.types.pos.PUNC;
import common.types.pos.V;
import corr.annotationAccess.annotators.SubiteratorExampleAnnotator;


/**
 * Lists of tests to demonstrate the various ways of accessing annotations 
 * from the annotation index
 * 
 * @author hernandez
 *
 */
public class AnnotationAccessTests {

	/**
	 * Iterate over the annotation index 
	 * with annotations at the same spans coming up in an undefined order 
	 * @throws Exception
	 */
	@Test
	public void annotationIndexEchoWoTypePriority() throws Exception{

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), null, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(AnnotationIndexEchoAnnotator.class)).process(jcas);
	}

	/**
	 * Iterate over the annotation index 
	 * with a type priority defined 
	 * to sort the annotations coming up at the same spans
	 * @throws Exception
	 */
	@Test
	public void annotationIndexEchoWiTypePriority() throws Exception{

		// Define a type priority
		TypePriorities aTypePriority = createTypePriorities(Sentence.class, Constituent.class, Token.class, POS.class);

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), aTypePriority, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(AnnotationIndexEchoAnnotator.class)).process(jcas);
	}

	/**
	 * Iterate over the annotation index with low level methods
	 * with annotations at the same spans coming up in an undefined order 
	 * @throws Exception
	 */
	@Test
	public void annotationIndexIteratorEchoWoTypePriority() throws Exception{

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), null, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(AnnotationIndexIteratorEchoAnnotator.class)).process(jcas);
	}

	/**
	 * Iterate over the annotation index with low level methods 
	 * with a type priority defined 
	 * to sort the annotations coming up at the same spans
	 * @throws Exception
	 */
	@Test
	public void annotationIndexIteratorEchoWiTypePriority() throws Exception{

		// Define a type priority
		TypePriorities aTypePriority = createTypePriorities(Sentence.class, Constituent.class, Token.class, POS.class);

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), aTypePriority, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(AnnotationIndexIteratorEchoAnnotator.class)).process(jcas);
	}
	
	
	/**
	 * Test the uima native subiterator method 
	 * without a type priority definition
	 * @throws Exception
	 */
	@Test
	public void subiteratorWoTypePriority() throws Exception{

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), null, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(SubiteratorExampleAnnotator.class)).process(jcas);
	}

	/**
	 * Test the uima native subiterator method 
	 * with a type priority definition
	 * @throws Exception
	 */
	@Test
	public void subiteratorWiTypePriority() throws Exception{

		// Define a type priority
		TypePriorities aTypePriority = createTypePriorities(Sentence.class, Constituent.class, Token.class, POS.class);

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), aTypePriority, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(SubiteratorExampleAnnotator.class)).process(jcas);
	}

	/**
	 * Test the uimaFIT selectCovered method which functionally is equivalent 
	 * to the UIMA native subiterator method but which is sensitive to the 
	 * annotations occurring at the same spans
	 * @throws Exception
	 */
	@Test
	public void selectCoveredWoTypePriority() throws Exception{

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), null, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(SelectCoveredExampleAnnotator.class)).process(jcas);
	}

	/**
	 * Example of iterator based on a constraint 
	 * which aims at pre-filter the iterated annotations
	 * @throws Exception
	 */
	@Test
	public void constrainedIterator() throws Exception{

		// Create and populate a CAS
		JCas jcas = createCas(createTypeSystemDescription(), null, null).getJCas();
		populateCas(jcas);

		// Create a pipeline and run the pipeline
		createEngine(createEngineDescription(ConstrainedIteratorExampleAnnotator.class)).process(jcas);
	}

	/**
	 * Create a JCas resulting from manual analysis to be used as sample data
	 * Set the sofa string of JCas and add manually some annotations to the index
	 * @param jcas
	 */
	private static void populateCas(JCas jcas) {
		//-- Set text
		jcas.setDocumentText("Verne visited the seaport of Nantes.");

		//-- Add some annotations

		// Tokens
		new Token(jcas, 0, 5).addToIndexes(); 	// Verne
		new Token(jcas, 6, 13).addToIndexes(); 	// visited
		new Token(jcas, 14, 17).addToIndexes(); // the
		new Token(jcas, 18, 25).addToIndexes();	// seaport
		new Token(jcas, 26, 28).addToIndexes(); // of
		new Token(jcas, 29, 35).addToIndexes();	// Nantes
		new Token(jcas, 35, 36).addToIndexes();	// .

		// Some POS
		new NP(jcas, 0, 5).addToIndexes(); 	// Verne
		new V(jcas, 6, 13).addToIndexes(); 	// visited
		new ART(jcas, 14, 17).addToIndexes(); // the
		new NN(jcas, 18, 25).addToIndexes();	// seaport
		new PP(jcas, 26, 28).addToIndexes(); // of
		new NP(jcas, 29, 35).addToIndexes();	// Nantes
		new PUNC(jcas, 35, 36).addToIndexes();	// .

		// Constituents
		new Constituent(jcas, 0, 5).addToIndexes(); 	// Verne
		new Constituent(jcas, 6, 13).addToIndexes(); 	// visited
		new Constituent(jcas, 14, 35).addToIndexes();	// the seaport of Nantes
		new Constituent(jcas, 18, 25).addToIndexes();	// the seaport
		new Constituent(jcas, 26, 35).addToIndexes(); 	// of Nantes

		// NamedEntities
		new NamedEntity(jcas, 0, 5).addToIndexes(); 	// Verne
		new NamedEntity(jcas, 29, 35).addToIndexes(); 	// Nantes

		// Sentence
		new Sentence(jcas, 0, 36).addToIndexes();
	}

	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}

}
